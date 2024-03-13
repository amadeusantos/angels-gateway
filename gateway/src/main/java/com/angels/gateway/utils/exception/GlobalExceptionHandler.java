package com.angels.gateway.utils.exception;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<?> handleUnprocessableContentException(WebClientResponseException exception) {
        return ResponseEntity.status(exception.getStatusCode().value())
                .body(exception.getResponseBodyAs(Map.class));
    }

    @ExceptionHandler(BadGatewayException.class)
    @CacheEvict(value = "parameters", allEntries = true)
    public ResponseEntity<ErrorResponse> handleBadGatewayException(BadGatewayException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_GATEWAY.value()));
    }
}
