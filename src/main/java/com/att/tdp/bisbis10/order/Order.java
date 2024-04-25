package com.att.tdp.bisbis10.order;

import com.att.tdp.bisbis10.orderItems.OrderItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID orderId;
    private Long restaurantId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_orderItems_id", referencedColumnName = "orderId")
    private List<OrderItem> orderItems = new ArrayList<>();



    public Order() {
    }

    public Order(UUID orderId, Long restaurantId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
