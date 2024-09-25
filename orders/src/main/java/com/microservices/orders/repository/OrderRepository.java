package com.microservices.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.orders.entity.Orders;


@Repository
public interface OrderRepository  extends JpaRepository<Orders, Long>{

}
