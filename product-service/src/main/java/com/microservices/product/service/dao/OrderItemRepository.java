package com.microservices.product.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.product.service.entity.OrderItem;
import com.microservices.product.service.entity.Orders;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT oi.order FROM OrderItem oi JOIN oi.product p WHERE p.retailerId = :retailerId")
	List<Orders> findOrdersByRetailerId(@Param("retailerId") long retailerId);

}
