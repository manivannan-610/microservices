package com.microserviceDemo.moviecatalogservice.models;

import java.util.List;

public class RatingList {

    public void setRating(List<Rating> ratingLists) {
        this.ratingLists = ratingLists;
    }

    public List<Rating> getRatingLists() {
        return ratingLists;
    }

    public void setRatingLists(List<Rating> ratingLists) {
        this.ratingLists = ratingLists;
    }

    private List<Rating> ratingLists;
}
