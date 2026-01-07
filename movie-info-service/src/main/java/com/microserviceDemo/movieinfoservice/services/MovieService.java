package com.microserviceDemo.movieinfoservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microserviceDemo.movieinfoservice.models.Movie;
import com.microserviceDemo.movieinfoservice.repository.MoiveRepository;

@Service
public class MovieService implements MovieServiceImplement{

    @Autowired
    MoiveRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(int id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public ResponseEntity<?> removeById(int id) {
        if(movieRepository.findById(id).isPresent()){
            movieRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
