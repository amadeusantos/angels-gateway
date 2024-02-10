package com.angels.gateway.domain;

import com.angels.gateway.domain.enums.ParameterEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModelParameters {

    private List<ParameterEnum> parameters;

    public ModelParameters() {}

    public ModelParameters(List<ParameterEnum> parameters) {
        this.parameters = parameters;
    }

}
