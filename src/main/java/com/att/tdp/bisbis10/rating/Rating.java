package com.att.tdp.bisbis10.rating;

import jakarta.persistence.*;

@Entity
@Table
public class Rating {
    //TODO: fix that restaurantId will be foreign key - add avg
    @Id
    private Long restaurantId;
    private Float rating;

    public Rating() {
    }

    public Rating(Long restaurantId, Float rating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
