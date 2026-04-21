package com.microserviceDemo.ratingdataservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserviceDemo.ratingdataservice.models.Rating;
import com.microserviceDemo.ratingdataservice.services.RatingService;

@RestController
@RequestMapping("ratingsData")
public class MainController {
    @Autowired
    RatingService ratingService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);


    @GetMapping("/{userId}")
    public List<Rating> getMovieRating(@PathVariable("userId") int id){
        logger.info("Entered into rating service with userId {}", id);
        return ratingService.getRatingByUserId(id);
    }

    @GetMapping
    public List<Rating> getAllRating(){
        return ratingService.getAllRating();
    }

    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating){
        return ratingService.save(rating);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRating(@PathVariable int userId){
        return ratingService.removeRating(userId);
    }
}
