package com.microserviceDemo.movieinfoservice.BeanDts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.microserviceDemo.movieinfoservice.models.Movie;
import com.microserviceDemo.movieinfoservice.services.MovieService;


@Profile("dev")
@Configuration
public class CommonBean implements CommandLineRunner{
    @Autowired
    private MovieService movieService;


    public void run(String... args) throws Exception {
        System.out.println("running command line runner");
//        ExecutorService executorService = Executors.newFixedThreadPool(1000);
//        return args -> {
//            for(int i=300; i<900; i++){
//                Movie m = new Movie();
//                m.setId(i);
//                m.setName("billa");
//                m.setDescription("old");
//                executorService.submit(()->{
//                    movieService.save(m);
//                });
//            }
//        };
        Movie m = new Movie();
        m.setId(1001);
        m.setName("VADACHENNAI");
        m.setDescription("A film by vetrimaran");
        movieService.findById(1001).orElse(movieService.save(m));
    }

}
