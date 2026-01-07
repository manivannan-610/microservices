package com.microserviceDemo.moviecatalogservice.models;


public class CatalogItem {
    private String title;
    private String description;
    private Double rating;

    public CatalogItem() {
    }

    public CatalogItem(String title, String description, Double rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
