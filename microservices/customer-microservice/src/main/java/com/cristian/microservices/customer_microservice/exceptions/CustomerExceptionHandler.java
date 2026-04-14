package com.cristian.microservices.customer_microservice.exceptions;


import com.cristian.microservices.common_exceptions.ErrorResponse;
import com.cristian.microservices.common_exceptions.GlobalExceptionHandler;
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
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(CustomerNotFoundException e) {

        var errors = new HashMap<String, String>();
        var fieldName = "customer";
        errors.put(fieldName, e.getMessage());
        log.warn("Customer not found: {}", e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));

    }

}
