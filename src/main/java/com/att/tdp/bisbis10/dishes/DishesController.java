package com.att.tdp.bisbis10.dishes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurants/{restaurantId}/dishes")
public class DishesController {

    final private DishesService dishesService;

    @Autowired
    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping
    public List<Dishes> getDishesByRestaurantId(@PathVariable Long restaurantId){
        return dishesService.getDishesByRestaurantId(restaurantId);
    }

    @PostMapping
    public void addNewDish(@PathVariable Long restaurantId,
                           @RequestBody (required = true) Dishes dishes){
        dishes.setRestaurantId(restaurantId);
        dishesService.addNewDish(dishes);
    }

    @PutMapping(path = "{dishId}")
    public void updateRestaurantDish(@PathVariable Long restaurantId, @PathVariable Long dishId,
                                     @RequestParam (required = false) String description,
                                     @RequestParam (required = false) Integer price){
        System.out.println("The new price will be " + price + " and the new desc will be " + description);
        System.out.println("Restid is " + restaurantId + " dish id is " + dishId);
        dishesService.updateDishDescriptionOrPrice(restaurantId, dishId, description, price);
    }

    @DeleteMapping("{dishId}")
    public void deleteDish(@PathVariable Long restaurantId, @PathVariable Long dishId){
        dishesService.deleteDish(restaurantId, dishId);
    }
}
