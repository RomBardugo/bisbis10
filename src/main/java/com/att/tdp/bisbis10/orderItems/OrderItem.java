package com.att.tdp.bisbis10.orderItems;


import jakarta.persistence.*;

@Entity
@Table
public class OrderItem {//TODO: forign key dishId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long orderItemId;
    private Long dishId;
    private Integer amount;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Long dishId, Integer amount) {
        this.orderItemId = orderItemId;
        this.dishId = dishId;
        this.amount = amount;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
