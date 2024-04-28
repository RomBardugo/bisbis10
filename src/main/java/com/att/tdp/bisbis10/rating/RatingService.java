package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    final private RestaurantRepository restaurantRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository  = restaurantRepository;
    }


    public List<Rating> getAllRatings(){
        return ratingRepository.findAll();
    }

    public Rating findRatingByRestaurantId(Long restaurantId){
        Optional<Rating> ratingOptional = ratingRepository.findRatingByRestaurantId(restaurantId);
        return ratingOptional.orElse(null);
    }

    public void addNewRating(Rating rating){
        Optional<Restaurant> restaurant = restaurantRepository.findById(rating.getTempRestaurantId());
        if (restaurant.isEmpty()){
            throw new IllegalStateException("Restaurant rating dose not have rating");
        }
        rating.setRestaurant(restaurant.get());
        ratingRepository.save(rating);
    }
}
