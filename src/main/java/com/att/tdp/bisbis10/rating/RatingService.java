package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    final private RestaurantService restaurantService;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantService restaurantService) {
        this.ratingRepository = ratingRepository;
        this.restaurantService  = restaurantService;
    }


    public List<Rating> getAllRatings(){
        return ratingRepository.findAll();
    }

    public Rating findRatingByRestaurantId(Long restaurantId){
        Optional<Rating> ratingOptional = ratingRepository.findRatingByRestaurantId(restaurantId);
        if (ratingOptional.isEmpty()){
            throw new IllegalStateException("Restaurant rating dose not exists");
        }
        return ratingOptional.get();
    }

    public void addNewRating(Rating rating){
        Optional<Rating> restaurantRating = ratingRepository.findRatingByRestaurantId(rating.getRestaurantId());
        if (restaurantRating.isPresent()){
            throw new IllegalStateException("Restaurant rating already exists");
        }
        if (!restaurantService.restaurantExists(rating.getRestaurantId())){
            throw new IllegalStateException("Restaurant dose not exists");
        }
        ratingRepository.save(rating);
    }
}
