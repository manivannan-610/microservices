package com.microserviceDemo.moviecatalogservice.exception;

import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class CommonError {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> commonException(HttpClientErrorException ex){
        System.out.println("enter into error class");
        return ResponseEntity.badRequest().body("Error: "+ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> notFoundException(RuntimeException ex){
        System.out.println("enter into error class");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: "+ex.getMessage());
    }
}
