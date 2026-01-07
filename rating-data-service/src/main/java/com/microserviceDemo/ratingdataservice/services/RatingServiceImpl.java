package com.microserviceDemo.ratingdataservice.services;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.microserviceDemo.ratingdataservice.models.Rating;

public interface RatingServiceImpl {

    Optional<Rating> getRatings(int id);
    Rating save(Rating rating);
    ResponseEntity<?> removeRating(int id);
    List<Rating> getAllRating();
    List<Rating> getRatingByUserId(int userId);
}
