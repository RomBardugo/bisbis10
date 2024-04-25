package com.att.tdp.bisbis10.restaurant;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RestaurantConfig {

    @Bean
    CommandLineRunner commandLineRunner(RestaurantRepository repository){
        return args -> {
            Restaurant moon = new Restaurant("Moon", false, Arrays.asList("Sushi", "Japanese"));
            Restaurant moses = new Restaurant("Moses", false, Arrays.asList("Hamburger", "American"));

//            repository.saveAll(
//                    List.of(moon, moses)
//            );
        };
    }
}
