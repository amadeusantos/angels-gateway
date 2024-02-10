package com.angels.gateway.service;

import com.angels.gateway.domain.ModelParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelsService {

    public static final String PROTOCOL = "http://";
    public static final String PATH_PARAMETERS = "/parameters";

    public static final String PATH_PREDICT = "/predict";

    private final WebClient.Builder webClient;

    public ModelParameters parametersModel(String application) {
        Optional<ModelParameters> optionalModelParameters = webClient.build().get()
                .uri(PROTOCOL + application + PATH_PARAMETERS).retrieve()
                .bodyToMono(ModelParameters.class).blockOptional();
        return optionalModelParameters.get();
    }

    public Map modelPredict(String application, Map<String, ?> parameters) {
        Optional<Map> optionalModelParameters = webClient.build().post()
                .uri(PROTOCOL + application + PATH_PREDICT)
                .bodyValue(parameters).retrieve().bodyToMono(Map.class).blockOptional();
        return optionalModelParameters.get();
    }

}
