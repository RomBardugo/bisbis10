package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.rating.Rating;
import com.att.tdp.bisbis10.rating.RatingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RatingService ratingService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, RatingService ratingService) {
        this.restaurantRepository = restaurantRepository;
        this.ratingService = ratingService;
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        restaurantList = this.getRestaurantRating(restaurantList);
        return restaurantList;
    }

    public void addNewRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)){
            throw new IllegalStateException("Restaurant with id " + restaurantId + " dose not exists");
        }
        restaurantRepository.deleteById(restaurantId);
    }

    @Transactional
    public void updateRestaurant(Long restaurantId, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isEmpty()){
            throw new IllegalStateException("There is no restaurants with the id of " + restaurantId);
        }
        Restaurant originalRestaurant = optionalRestaurant.get();
        if (restaurant.getName() != null & !Objects.equals(originalRestaurant.getName(), restaurant.getName())){
            originalRestaurant.setName(restaurant.getName());
        }
        if (restaurant.getIsKosher() != null & !Objects.equals(originalRestaurant.getIsKosher(),restaurant.getIsKosher())){
            originalRestaurant.setIsKosher(restaurant.getIsKosher());
        }
        if (restaurant.getCuisines() != null & !Objects.equals(originalRestaurant.getCuisines(),restaurant.getCuisines())){
            originalRestaurant.setCuisines(restaurant.getCuisines());
        }
        restaurantRepository.save(originalRestaurant);
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        Optional<List<Restaurant>> restaurants = restaurantRepository.findRestaurantsByCuisines(cuisine);
        if (restaurants.get().isEmpty()){
            throw new IllegalStateException("There is no restaurants with these cuisine");
        }
        return this.getRestaurantRating(restaurants.get());
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->
                new IllegalStateException("Restaurant with id " + restaurantId + " dose not exists"));
        Rating rating = ratingService.findRatingByRestaurantId(restaurant.getId());
        restaurant.setRating(rating);
        return restaurant;
    }

    private List<Restaurant> getRestaurantRating(List<Restaurant> restaurantList){
        for (Restaurant restaurant: restaurantList){
            Rating rating = ratingService.findRatingByRestaurantId(restaurant.getId());
            restaurant.setRating(rating);
        }
        return restaurantList;
    }
}
