package com.microserviceDemo.movieinfoservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.microserviceDemo.movieinfoservice.models.Movie;

public interface MoiveRepository extends JpaRepository<Movie, Integer> {

}
