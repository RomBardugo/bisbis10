package com.att.tdp.bisbis10.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

//    @Query("SELECT r FROM Restaurant r WHERE r.name = ?1")
    Optional<Restaurant> findRestaurantByName(String name);
}
