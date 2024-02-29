package com.angels.gateway.controller;

import com.angels.gateway.service.PredictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(example = "{\n" +
            "   \"previous_weight\": 0,\n" +
            "  \"gestational_risk\": 0,\n" +
            "  \"schooling\": 0,\n" +
            "  \"has_hypertension\": true,\n" +
            "  \"has_diabetes\": true,\n" +
            "  \"has_pelvic_sugery\": true,\n" +
            "  \"has_urinary_infection\": true,\n" +
            "  \"has_congenital_malformation\": true,\n" +
            "  \"has_family_twinship\": true,\n" +
            "  \"amount_gestation\": 0,\n" +
            "  \"amount_abortion\": 0,\n" +
            "  \"amount_deliveries\": 0,\n" +
            "  \"amount_cesarean\": 0,\n" +
            "  \"target\": 0,\n" +
            "  \"age\": 0,\n" +
            "  \"fist_prenatal\": 0,\n" +
            "  \"time_between_pregnancies\": 0\n" +
            "}"))))
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(example = "{\n \"model-1\": {}, \n \"model-2\": {} \n }")))
    public ResponseEntity<Map<String, ?>> multiPredicts(@RequestBody Map<String, ?> parameters) {
        return ResponseEntity.ok(service.MultiPredicts(parameters));
    }
}
