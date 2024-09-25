package com.microservices.orders.service;

import java.math.BigDecimal;

import com.microservices.orders.entity.Orders;

public interface OrderService {

	Orders placeOrder(Orders order, Long buyerId);

}
