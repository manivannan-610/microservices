package com.microserviceDemo.moviecatalogservice.Services;

import com.microserviceDemo.moviecatalogservice.models.CatalogItem;
import com.microserviceDemo.moviecatalogservice.models.Movie;
import com.microserviceDemo.moviecatalogservice.models.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.NotFoundException;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mainservice{

    private RestTemplate restTemplate;
    public Mainservice(MainServiceClient mainServiceClient, RestTemplateBuilder restTemplateBuilder) {
        this.mainServiceClient = mainServiceClient;
        this.restTemplate = restTemplateBuilder.build();
    }

    MainServiceClient mainServiceClient;


    public Flux<CatalogItem> getDetails(String userId){
        System.out.println("enter into fucntion 4");
       try {
           return mainServiceClient.getRatings(userId).flatMap(rating-> mainServiceClient.getMovieById(rating.getMovieId()).
                           map(movie -> new CatalogItem(movie.getName(),
                                   movie.getDescription(), rating.getRating())))
                   .switchIfEmpty(Mono.error(new RuntimeException("no movie details on this userId.")));
       }catch(Exception ex){
           throw new RuntimeException("Failed to fetch details");
       }
    }

    public Flux<Object> getCountries() throws Exception {
        return mainServiceClient.getCountries();
    }

}