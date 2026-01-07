package com.microserviceDemo.movieinfoservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.microserviceDemo.movieinfoservice.models.Movie;

public interface MovieServiceImplement {
    Movie save(Movie moive);
    Optional<Movie> findById(int id);
    List<Movie> findAll();
    ResponseEntity<?> removeById(int id);
}
