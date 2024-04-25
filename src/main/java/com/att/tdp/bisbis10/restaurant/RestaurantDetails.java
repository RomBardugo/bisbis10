package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.dishes.Dishes;

import java.util.List;

public class RestaurantDetails {

    public Restaurant restaurant;
    public List<Dishes> dishes;

    public RestaurantDetails() {
    }

    public RestaurantDetails(Restaurant restaurant, List<Dishes> dishes) {
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }
}
