package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.product.service.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
