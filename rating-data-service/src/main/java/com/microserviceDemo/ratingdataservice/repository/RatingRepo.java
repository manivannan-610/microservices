package com.microserviceDemo.ratingdataservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microserviceDemo.ratingdataservice.models.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

    @Query(nativeQuery = true, value = "select * from rating where user_id = :user_id")
    public List<Rating> getRatingByUserId(@Param("user_id") int user_id);

}
