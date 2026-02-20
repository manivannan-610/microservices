package com.microserviceDemo.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.microserviceDemo.moviecatalogservice.Services.Mainservice;
import org.apache.http.HttpStatus;
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
import reactor.core.publisher.Flux;

import javax.net.ssl.SSLContext;


@RestController
@RequestMapping("catalog")
public class MainController {

    @Autowired
    @Qualifier("restOne")
    private RestTemplate restTemplateOne;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private Mainservice mainservice;

    @GetMapping("{userId}")
    @CircuitBreaker(name = "countriesCircuitBreaker", fallbackMethod = "getUsersMovieList")
    public ResponseEntity<Flux<CatalogItem>>  getUsersMovieList(@PathVariable("userId") String userId) throws Exception {
            System.out.println("enter into fucntion 1");
            return ResponseEntity.status(HttpStatus.SC_OK).body(mainservice.getDetails(userId));
    }

    @GetMapping("/countries")
    public Flux<Object> getCountries() throws Exception {
        return mainservice.getCountries();
    }
}