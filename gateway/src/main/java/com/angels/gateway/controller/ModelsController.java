package com.angels.gateway.controller;

import com.angels.gateway.controller.response.ModelParametersResponse;
import com.angels.gateway.service.ModelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelsController {

    private final ModelsService modelsService;

    @GetMapping("/{application}")
    public ResponseEntity<ModelParametersResponse> parametersModel(@PathVariable String application) {
        return ResponseEntity.ok(new ModelParametersResponse(modelsService.parametersModel(application)));
    }

    @PostMapping("/{application}")
    public ResponseEntity<?> modelPredict(@PathVariable String application, @RequestBody Map<String, ?> parameters) {
        return ResponseEntity.ok(modelsService.modelPredict(application, parameters));
    }

}
