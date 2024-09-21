package com.microservices.orders.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.orders.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
