package com.angels.gateway.service;

import com.angels.gateway.domain.ModelRisk;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PredictService {

    private final EurekaClient eurekaClient;

    private final ModelsService modelsService;

    public List<ModelRisk> MultiPredicts(Map<String, ?> parameters) {
        List<String> models =  eurekaClient.getApplications().getRegisteredApplications().stream()
                .map(application -> application.getName().toLowerCase())
                .filter(name -> name.startsWith("model")).toList();
        List<ModelRisk> responseModels = new ArrayList<>();
        List<Boolean> check = models.parallelStream().map(model -> toCheck(responseModels, model, parameters)).toList();
        return responseModels;
    }

    private Map<String, ?> requestBuild(Map<String, ?> parameters, List<String> parametersModel) {
        Map<String, Object> request = new HashMap<>();
        for (String parameter: parametersModel) {
            request.putIfAbsent(parameter, parameters.get(parameter));
        }
        return request;
    }

    private boolean toCheck(List<ModelRisk> responseModels, String model, Map<String, ?> parameters) {
        List<String> parametersModel = modelsService.parametersModel(model);
        if (parametersModel.stream().allMatch(parameters::containsKey)) {
            Map<String, ?> request = requestBuild(parameters, parametersModel);
            ModelRisk modelRisk = modelsService.modelPredict(model, request);
            if (modelRisk.getPrediction() == 1) {
                modelRisk.setModel(model);
                responseModels.add(modelRisk);
            }
        }
        return true;
    }

}
