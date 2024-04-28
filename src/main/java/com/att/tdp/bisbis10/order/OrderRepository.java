package com.att.tdp.bisbis10.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findOrdersByRestaurantId(Long restaurantId);
}
