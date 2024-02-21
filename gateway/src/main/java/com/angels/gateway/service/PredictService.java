package com.angels.gateway.service;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PredictService {

    private final EurekaClient eurekaClient;

    private final ModelsService modelsService;

    public Map<String, Map> MultiPredicts(Map<String, ?> parameters) {
        List<String> models =  eurekaClient.getApplications().getRegisteredApplications().stream()
                .map(application -> application.getName().toLowerCase())
                .filter(name -> name.startsWith("model")).toList();
        Map<String, Map> responseModels = new HashMap<>();
        for (String model: models) {
            List<String> parametersModel = modelsService.parametersModel(model);
            if (parametersModel.stream().allMatch(parameters::containsKey)) {
                Map<String, ?> request = requestBuild(parameters, parametersModel);
                responseModels.put(model,modelsService.modelPredict(model, request));
            }
        }
        return responseModels;
    }

    private Map<String, ?> requestBuild(Map<String, ?> parameters, List<String> parametersModel) {
        Map<String, Object> request = new HashMap<>();
//        for (String parameter: parametersModel) {
//            request.putIfAbsent(parameter, parameters.get(parameter));
//        }
        return request;
    }

}
