package com.att.tdp.bisbis10.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Restaurant {
    @Id
    @SequenceGenerator(
            name = "restaurant_sequence",
            sequenceName = "restaurant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurant_sequence"
    )
    private Long id;
    private String name;
    @Transient
    private Float rating;
    @JsonProperty("isKosher")
    private Boolean isKosher;
    @ElementCollection
    private List<String> cuisines = new ArrayList<>();


    public Restaurant(){
    }

    public Restaurant(Long id, String name, Boolean isKosher, List<String> cuisines) {
        this.id = id;
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public Restaurant(String name, Boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isKosher=" + isKosher +
                ", cuisines=" + cuisines +
                '}';
    }
}
