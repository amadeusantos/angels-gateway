package com.angels.gateway.service;

import com.angels.gateway.domain.ModelsResponseDTO;
import com.angels.gateway.utils.exception.ModelResponseException;
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

    public ModelsResponseDTO MultiPredicts(Map<String, ?> parameters) {
        List<String> models =  eurekaClient.getApplications().getRegisteredApplications().stream()
                .map(application -> application.getName().toLowerCase())
                .filter(name -> name.startsWith("model")).toList();
        Map<String, Map> responseModels = new HashMap<>();
        
        List<Map<String, Object>> modelsAndRisk = models.parallelStream().map(model -> toCheck(responseModels, model, parameters)).toList();
        return new ModelsResponseDTO(responseModels, getModelsMap(modelsAndRisk));
    }

    private Map<String, ?> requestBuild(Map<String, ?> parameters, List<String> parametersModel) {
        Map<String, Object> request = new HashMap<>();

        for (String parameter: parametersModel) {
            request.putIfAbsent(parameter, parameters.get(parameter));
        }
        return request;
    }

    private Map<String, Object> toCheck(Map<String, Map> responseModels, String model, Map<String, ?> parameters) {
        List<String> parametersModel = modelsService.parametersModel(model);
        if (parametersModel.stream().allMatch(parameters::containsKey)) {
            Map<String, ?> request = requestBuild(parameters, parametersModel);
            responseModels.put(model,modelsService.modelPredict(model, request));
        }
        return checkForModelsRisk(model, responseModels);
        
    }

    private Map<String, Object> checkForModelsRisk(String modelResponse, Map<String, Map> responseModels) {
        if (!modelResponse.isEmpty()  && !responseModels.isEmpty()) {
            Object riskObject =  responseModels.get(modelResponse).get("prediction");
        Map<String, Object> modelsAndRisk = new HashMap<>();
        
        if (riskObject instanceof Boolean) {
            boolean riskValue = (Boolean) riskObject;
            if (riskValue) {
                modelsAndRisk.put(modelResponse, responseModels.get(modelResponse).get("probability"));
            }
        } else {
            throw new ModelResponseException("An error occurred when getting model's responses");
        }
        return modelsAndRisk;
        } else {
            
            return new HashMap<>();
        }        
    }

    private Map<String, Object> getModelsMap(List<Map<String, Object>> modelsAndRisk) {
        
        Map<String, Object> modelsMap = new HashMap<>(); 
        for (int i = 0; i < modelsAndRisk.size(); i++) {
            for (Map.Entry<String, Object> set: modelsAndRisk.get(i).entrySet()) {
                modelsMap.put(set.getKey(), set.getValue());
            }
        }
        return modelsMap;
    }
}