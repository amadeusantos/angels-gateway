package com.angels.gateway.controller;

import com.angels.gateway.service.PredictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/predict")
@RequiredArgsConstructor
public class PredictController {

    private final PredictService service;

    @PostMapping
    public ResponseEntity<Map<String, ?>> multiPredicts(@RequestBody Map<String, ?> parameters) {
        return ResponseEntity.ok(service.MultiPredicts(parameters));
    }
}
