package com.microserviceDemo.ratingdataservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeId;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @Column(name="movie_id")
    private int movieId;

    @Column(name="user_id")
    private int user_id;

    private Double rating;

    public Rating() {
    }

    public Rating(int movieId, int user_id, Double rating) {
        this.movieId = movieId;
        this.user_id = user_id;
        this.rating = rating;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
