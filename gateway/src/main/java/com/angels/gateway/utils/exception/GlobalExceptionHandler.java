package com.angels.gateway.utils.exception;

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

}
