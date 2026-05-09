package com.cristian.microservices.cart_microservice.exceptions;

import com.cristian.microservices.common_exceptions.ErrorResponse;
import com.cristian.microservices.common_exceptions.GlobalExceptionHandler;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
@Primary
public class CartExceptionHandler extends GlobalExceptionHandler {
    @ExceptionHandler(CartException.class)
    public ResponseEntity<ErrorResponse> handle(CartException e) {
        var errors = new HashMap<String, String>();
        var fieldName = "cart-service";
        errors.put(fieldName, e.getMessage());
        log.warn("Cart error: {}", e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handle(FeignException e) {
        var errors = new HashMap<String, String>();
        var fieldName = "Error Communicating with microservice";
        errors.put(fieldName, e.getMessage());
        log.warn("Error Communicating with microservice: {}", e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
