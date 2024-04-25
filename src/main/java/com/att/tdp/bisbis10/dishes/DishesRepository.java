package com.att.tdp.bisbis10.dishes;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {

    Optional<List<Dishes>> findDishesByRestaurantId(Long restaurantId);
    Optional<Dishes> findDishesByRestaurantIdAndDishId(Long restaurantId, Long dishId);
}
