package com.microserviceDemo.moviecatalogservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CommonError {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> commonException(HttpClientErrorException ex){
        System.out.println("enter into error class");
        return ResponseEntity.badRequest().body("Error: "+ex.getMessage());
    }
}
