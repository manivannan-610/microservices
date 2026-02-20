package com.microserviceDemo.moviecatalogservice.Services;

import com.microserviceDemo.moviecatalogservice.models.CatalogItem;
import com.microserviceDemo.moviecatalogservice.models.Movie;
import com.microserviceDemo.moviecatalogservice.models.Rating;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@Service
public class MainServiceClient {

    private WebClient webClient;
    private WebClient webClient1;
    int i = 1;
    //    @Autowired
    private ReactiveCircuitBreaker reactiveCircuitBreaker;

    public MainServiceClient(WebClient.Builder builder, ReactiveCircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.webClient = builder.baseUrl("http://localhost:8081/").build();
        this.webClient1 = builder.baseUrl("https://restcountries.com/").build();
        this.reactiveCircuitBreaker = circuitBreakerFactory.create("dep");
        ;
    }

//    public Flux<Rating> getRatings(String userId){
//        System.out.println("enter into fucntion 2");
//        return reactiveCircuitBreaker.run(webClient.get().uri("/ratingsData/{userId}", userId).headers(header->
//                        header.setBasicAuth("admin","admin@123"))
//                .retrieve()
//                .bodyToFlux(Rating.class), throwable->{
//                System.out.println("rating error ="+throwable.getMessage());
//                return Flux.just(new Rating("1001", 1.1));
//        });
//    }

    @CircuitBreaker(name = "ratingService", fallbackMethod = "fallBackRatings")
    public Flux<Rating> getRatings(String userId) {
        System.out.println("enter into fucntion 2");
        return webClient.get().uri("/ratingsData/{userId}", userId).headers(header ->
                        header.setBasicAuth("admin", "admin@123"))
                .retrieve()
//                .onStatus(HttpStatusCode::isError, response ->
//                        response.bodyToMono(String.class)
//                                .flatMap(body -> Mono.error(new RuntimeException("Gateway error: " + body)))
//                )
                .bodyToFlux(Rating.class);
    }

    public Flux<Rating> fallBackRatings(String userId, Throwable t) {
        System.out.println("error in side fall back rating="+ t.getMessage());
        return Flux.just(new Rating("1001", 1.1));
    }

//    public Mono<Movie> getMovieById(String movieId){
//        System.out.println("enter into fucntion 3");
//        return reactiveCircuitBreaker.run(webClient.get().uri("/movies/{movieId}", movieId).headers(header->
//                        header.setBasicAuth("admin","admin@123")).retrieve().
//                bodyToMono(Movie.class),throwable -> {
//            System.out.println("error in movie="+ throwable.getMessage());
//            return Mono.just(new Movie(100, "nomovie", "fuck off"));
//        });
//    }

    @CircuitBreaker(name = "movieService", fallbackMethod = "fallBackMovies")
    public Mono<Movie> getMovieById(String movieId) {
        System.out.println("enter into fucntion 3");
        return webClient.get().uri("/movies/{movieId}", movieId).headers(header ->
                        header.setBasicAuth("admin", "admin@123")).retrieve()
//                .onStatus(HttpStatusCode::isError, response ->
//                        response.bodyToMono(String.class)
//                                .flatMap(body -> Mono.error(new RuntimeException("Gateway error: " + body)))
//                )
                .bodyToMono(Movie.class);
    }

    public Mono<Movie> fallBackMovies(String userId, Throwable t) {
        return Mono.just(new Movie(100, "nomovie", "fuck off"));
    }

//    public Flux<Object> getCountries() throws Exception {
//        Flux<Object> countries = null;
//        System.out.println("enter into call="+ i++);
//        return reactiveCircuitBreaker.run(webClient1.get()
//                .uri("/v3.1/all").retrieve().bodyToFlux(Object.class), throwable -> {
//            System.out.println("error ="+throwable.getMessage());
//            return Flux.just("nothung");
//        });
//    }

    @CircuitBreaker(name = "countriesCircuitBreaker", fallbackMethod = "getCountries")
    public Flux<Object> getCountries() throws Exception {
        Flux<Object> countries = null;
        try {
            System.out.println("einter into method=" + i++);
            countries = webClient1.get().uri("/v3.1/all").retrieve().bodyToFlux(Object.class);
        } catch (Exception e) {
            throw new Exception("Failed to fetch countries from the API");
        }
        return countries;
    }

    public Flux<Object> getCountries(Throwable throwable) {
        return Flux.just("no response");
    }

}
