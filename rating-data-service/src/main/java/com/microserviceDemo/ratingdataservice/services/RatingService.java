package com.microserviceDemo.ratingdataservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microserviceDemo.ratingdataservice.models.Rating;
import com.microserviceDemo.ratingdataservice.repository.RatingRepo;

@Service
public class RatingService implements RatingServiceImpl{
    @Autowired
    RatingRepo ratingRepo;


    @Override
    public Optional<Rating> getRatings(int id) {
        return ratingRepo.findById(id);
    }

    public Rating save(Rating rating) {
       return ratingRepo.save(rating);
    }

    public ResponseEntity<?> removeRating(int id) {
        if(ratingRepo.findById(id).isPresent()){
            ratingRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(int userId) {
        return ratingRepo.getRatingByUserId(userId);
    }

}
