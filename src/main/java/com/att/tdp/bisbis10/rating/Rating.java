package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    @OneToOne
    @JoinColumn(name = "fk_restaurant_id")
    private Restaurant restaurant;
    @Transient
    @JsonProperty("restaurantId")
    private Long tempRestaurantId;
    private Float rating;

    public Rating() {
    }

    public Rating(Restaurant restaurant, Float rating) {
        this.restaurant = restaurant;
        this.rating = rating;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Long getTempRestaurantId() {
        return tempRestaurantId;
    }

    public void setTempRestaurantId(Long tempRestaurantId) {
        this.tempRestaurantId = tempRestaurantId;
    }

}

