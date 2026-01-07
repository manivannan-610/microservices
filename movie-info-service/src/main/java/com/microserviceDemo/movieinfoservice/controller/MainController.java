package com.microserviceDemo.movieinfoservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microserviceDemo.movieinfoservice.models.Movie;
import com.microserviceDemo.movieinfoservice.repository.MoiveRepository;
import com.microserviceDemo.movieinfoservice.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MainController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{movieId}")
    public Optional<Movie> getMovieInfo(@PathVariable("movieId") int id){
        return movieService.findById(id);
    }

    @GetMapping("/")
    public List<Movie> getMovieAllInfo(){
        return movieService.findAll();
    }

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie moive){
       return movieService.save(moive);
    }

    @DeleteMapping("/removeMovie/{movieId}")
    public ResponseEntity<?> removeMovie(@PathVariable("movieId") int movieId){
        return movieService.removeById(movieId);
    }
}
