package com.angels.gateway.controller.response;

import com.angels.gateway.domain.ModelParameters;
import com.angels.gateway.domain.enums.ParameterEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class ModelParametersResponse {

    private final List<String> parameters;
    public ModelParametersResponse(ModelParameters modelParameters) {
        this.parameters = modelParameters.getParameters().stream().map(this::converterToString).toList();
    }

    private String converterToString(ParameterEnum parameterEnum) {
        return parameterEnum.name().toLowerCase();
    }
}
