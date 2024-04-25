package com.att.tdp.bisbis10.rating;


import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingController {

    final private RatingService ratingService;


    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<Rating> getAllRatings(){
        return ratingService.getAllRatings();
    }

    @PostMapping
    public void addNewRating(@RequestBody Rating rating){
        ratingService.addNewRating(rating);
    }
}
