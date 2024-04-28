package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.rating.Rating;
import com.att.tdp.bisbis10.rating.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, RatingRepository ratingRepository) {
        this.restaurantRepository = restaurantRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        restaurantList = this.getRestaurantRating(restaurantList);
        return restaurantList;
    }

    public Boolean restaurantExists(Long restaurantId){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        return restaurantOptional.isPresent();
    }
    public void addNewRestaurant(Restaurant restaurant) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantByName(restaurant.getName());
        if (restaurantOptional.isPresent()){
            throw new IllegalStateException("Restaurant name is taken");
        }
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)){
            throw new IllegalStateException("Restaurant with id " + restaurantId + " dose not exists");
        }
        restaurantRepository.deleteById(restaurantId);
    }

    @Transactional
    public void updateRestaurant(Long restaurantId, List<String> cuisines) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new IllegalStateException("Restaurant with id " + restaurantId + " dose not exists"));
        restaurant.setCuisines(cuisines);
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        Optional<List<Restaurant>> restaurants = restaurantRepository.findRestaurantsByCuisines(cuisine);
        if (restaurants.isEmpty()){
            new IllegalStateException("There is no restaurants with these cuisine");
        }
        return this.getRestaurantRating(restaurants.get());
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new IllegalStateException("Restaurant with id " + restaurantId + " dose not exists"));
        Optional<Rating> ratingOptional = ratingRepository.findRatingByRestaurantId(restaurant.getId());
        ratingOptional.ifPresent(rating -> restaurant.setRating(rating.getRating()));
        return restaurant;
    }

    private List<Restaurant> getRestaurantRating(List<Restaurant> restaurantList){
        for (Restaurant restaurant: restaurantList){
            Optional<Rating> ratingOptional = ratingRepository.findRatingByRestaurantId(restaurant.getId());
            System.out.println("for restaurant " + restaurant.getName() + " the rating is " + ratingOptional);
            ratingOptional.ifPresent(rating -> restaurant.setRating(rating.getRating()));
        }
        return restaurantList;
    }
}
