package com.microserviceDemo.ratingdataservice.configuration;

import com.microserviceDemo.ratingdataservice.models.Rating;
import com.microserviceDemo.ratingdataservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration  implements CommandLineRunner {
    @Autowired
    RatingService ratingService;

    @Override
    public void run(String... args) throws Exception {
        Rating r = new Rating();
        r.setMovieId(1001);
        r.setUser_id(101);
        r.setRating(9.0);
        if(ratingService.getRatingByUserId(101) != null){
            ratingService.save(r);
        }
    }
}
