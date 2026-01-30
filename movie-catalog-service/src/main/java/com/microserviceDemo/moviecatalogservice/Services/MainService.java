package com.microserviceDemo.moviecatalogservice.Services;

import com.microserviceDemo.moviecatalogservice.models.CatalogItem;
import com.microserviceDemo.moviecatalogservice.models.Movie;
import com.microserviceDemo.moviecatalogservice.models.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mainservice{

    private WebClient webClient;

    public Mainservice(WebClient.Builder builder){
        this.webClient = builder.baseUrl("http://localhost:8081/").build();
    }

    public Flux<Rating> getRatings(String userId){
                System.out.println("enter into fucntion 2");
        return webClient.get().uri("/ratingsData/{userId}", userId).headers(header->
                      header.setBasicAuth("admin","admin@123"))
                .retrieve().bodyToFlux(Rating.class);
    }

    public Mono<Movie> getMovieById(String movieId){
                System.out.println("enter into fucntion 3");
        return webClient.get().uri("/movies/{movieId}", movieId).headers(header->
                header.setBasicAuth("admin","admin@123")).retrieve().bodyToMono(Movie.class);
    }

    public Flux<CatalogItem> getDetails(String userId){
        System.out.println("enter into fucntion 4");

        return this.getRatings(userId).flatMap(rating-> this.getMovieById(rating.getMovieId()).
                map(movie -> new CatalogItem(movie.getName(),
                        movie.getDescription(), rating.getRating())));
    }
}