package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.product.service.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
