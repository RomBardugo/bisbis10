package com.att.tdp.bisbis10.dishes;

import com.att.tdp.bisbis10.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishesService {

    final private DishesRepository dishesRepository;

    @Autowired
    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    public List<Dishes> getDishesByRestaurantId(Long restaurantId) {
        Optional<List<Dishes>> optionalDishes =  dishesRepository.findDishesByRestaurantId(restaurantId);
        if (optionalDishes.isEmpty()){
            throw new IllegalStateException("Restaurant dose not have any dishes");
        }
        return optionalDishes.get();
    }

    public void addNewDish(Dishes dishes) {
        dishesRepository.save(dishes);
    }

    public void updateDishDescriptionOrPrice(Long restaurantId, Long dishId, String description, Integer price) {
        Optional<Dishes> dishesOptional = dishesRepository.findDishesByRestaurantIdAndDishId(restaurantId, dishId);
        if (dishesOptional.isEmpty()){
            throw new IllegalStateException("Dish dose not exist in this restaurant");
        }
        Dishes dish = dishesOptional.get();
        if (description != null){
            System.out.println("UPDATING DISCRIPTION");
            dish.setDescription(description);
        }
        if (price != null){
            System.out.println("UPDATING PRICE");
            dish.setPrice(price);
        }
        dishesRepository.save(dish);
    }

    public void deleteDish(Long restaurantId, Long dishId) {
        Optional<Dishes> dish = dishesRepository.findDishesByRestaurantIdAndDishId(restaurantId, dishId);
        if (dish.isEmpty()){
            throw new IllegalStateException("Dish dose not exist in this restaurant");
        }
        dishesRepository.delete(dish.get());
    }
}
