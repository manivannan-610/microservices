package com.microserviceDemo.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import com.microserviceDemo.moviecatalogservice.models.CatalogItem;
import com.microserviceDemo.moviecatalogservice.models.Rating;
import com.microserviceDemo.moviecatalogservice.models.Movie;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import javax.net.ssl.SSLContext;


@RestController
@RequestMapping("catalog")
public class MainController {

    @Autowired
    private RestTemplate restTemplateOne;

    @Autowired
    private WebClient.Builder webClientBuilder;
//    @Autowired
//    SSLContext sslContext;

    @GetMapping("{userId}")
    @Retry(name = "getUsersMovieList", fallbackMethod = "falBackMovieList")
    public List<CatalogItem> getUsersMovieList(@PathVariable("userId") String userId) throws HttpClientErrorException {
        try {
        System.out.println("enter into fucntion");
        ResponseEntity<Rating[]> responses =
                restTemplateOne.getForEntity("http://rating-data-service/ratingsData/" + userId, Rating[].class);
        List<Rating> ratingList = Arrays.asList(responses.getBody());
        ratingList.stream().forEach(attr -> {
            System.out.println(attr.getMovieId());
            System.out.println(attr.getRating());
        });
        List<CatalogItem> catalogItemLst = new ArrayList<>();
            for (Rating rating : ratingList) {
                System.out.println("ratingId=" + rating.getMovieId());
                Movie movie = restTemplateOne.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                if (movie == null) {
                    continue;
                }
                CatalogItem catalogItem = new CatalogItem();
                catalogItem.setTitle(movie.getName());
                catalogItem.setDescription(movie.getDescription());
                catalogItem.setRating(rating.getRating());
                catalogItemLst.add(catalogItem);
                System.out.println("movieId=" + movie.getName());
            }
            return catalogItemLst;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<CatalogItem> falBackMovieList(String userId, Exception ex) {
        return Arrays.asList(new CatalogItem("No Movie", "", 0.0));
    }
}