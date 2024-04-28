package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.order.Order;
import com.att.tdp.bisbis10.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<List<Restaurant>> findRestaurantsByCuisines(String cuisine);
}
