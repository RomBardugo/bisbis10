package com.att.tdp.bisbis10.dishes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table
public class Dishes { //TODO: forign key restaurant

    @Id
    @SequenceGenerator(
            name = "dish_sequence",
            sequenceName = "dish_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dish_sequence"
    )
    @JsonProperty("id")
    private Long dishId;
    @JsonIgnore
    private Long restaurantId;
    private String name;
    private String description;
    private Integer price;

    public Dishes() {
    }

    public Dishes(Long dishId, Long restaurantId, String name, String description, Integer price) {
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dishes(Long restaurantId, String name, String description, Integer price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getDishId() {
        return dishId;
    }


    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "dishId=" + dishId +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
