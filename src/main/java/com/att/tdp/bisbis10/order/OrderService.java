package com.att.tdp.bisbis10.order;

import com.att.tdp.bisbis10.dishes.Dishes;
import com.att.tdp.bisbis10.dishes.DishesService;
import com.att.tdp.bisbis10.orderItems.OrderItem;
import com.att.tdp.bisbis10.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    final private OrderRepository orderRepository;
    final private RestaurantService restaurantService;
    final private DishesService dishesService;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantService restaurantService, DishesService dishesService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.dishesService = dishesService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addNewOrder(Order order) {
        if (!restaurantService.restaurantExists(order.getRestaurantId())){
            throw new IllegalStateException("Restaurant dose not exists");
        }
        List<Dishes> dishes = dishesService.getDishesByRestaurantId(order.getRestaurantId());
        List<Long> dishIds = dishes.stream().map(Dishes::getDishId).toList();
        for (OrderItem orderItem : order.getOrderItems()){
            if (!dishIds.contains(orderItem.getDishId())){
                throw new IllegalStateException("Restaurant dose not have dish number " + orderItem.getDishId());
            }
        }
        orderRepository.save(order);
    }

    public List<Order> getOrdersByRestaurantId(Long restaurantId) {
        Optional<List<Order>> orderOptional = orderRepository.findOrdersByRestaurantId(restaurantId);
        if (orderOptional.isEmpty()){
            throw new IllegalStateException("Restaurant dose not have any orders");
        }
        return orderOptional.get();
    }
}
