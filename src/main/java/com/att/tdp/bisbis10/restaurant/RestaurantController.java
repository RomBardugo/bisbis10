package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.dishes.Dishes;
import com.att.tdp.bisbis10.dishes.DishesService;
import com.att.tdp.bisbis10.rating.Rating;
import com.att.tdp.bisbis10.rating.RatingController;
import com.att.tdp.bisbis10.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final DishesService dishesService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService, DishesService dishesService) {
        this.restaurantService = restaurantService;
        this.dishesService = dishesService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants(@RequestParam(required = false) String cuisine,
                                           @RequestParam(required = false) Long id){
        if (cuisine != null){
            return restaurantService.getRestaurantsByCuisine(cuisine);
        }
        return restaurantService.getRestaurants();

    }

    @GetMapping("/{restaurantId}")
    public RestaurantDetails getRestaurantById(@PathVariable("restaurantId") Long restaurantId){
        Restaurant restaurant =  restaurantService.getRestaurantById(restaurantId);
        List<Dishes> dishes = dishesService.getDishesByRestaurantId(restaurantId);
        return new RestaurantDetails(restaurant, dishes);
    }

    @PostMapping
    public ResponseEntity<String> registerNewRestaurant(@RequestBody Restaurant restaurant){
        restaurantService.addNewRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body("New restaurant created successfully");
    }

    @DeleteMapping(path = "{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("restaurantId") Long restaurantId){
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{restaurantId}")
    public void updateRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                @RequestParam(required= true) List<String> cuisines){
        restaurantService.updateRestaurant(restaurantId, cuisines);
    }
}
