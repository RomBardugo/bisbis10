package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.dishes.Dishes;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetails {

    private Long id;
    private String name;
    private Float rating;
    @JsonProperty("isKosher")

    private Boolean isKosher;
    private List<String> cuisines;
    private List<Dishes> dishes;

    public RestaurantDetails() {
    }

    public RestaurantDetails(Long id, String name, Float rating, Boolean isKosher, List<String> cuisines, List<Dishes> dishes) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getIsKosher() {
        return isKosher;
    }

    public void setIsKosher(Boolean kosher) {
        isKosher = kosher;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }
}
