package com.microservices.product.service.service;

import java.util.List;

import com.microservices.product.service.dto.CheckoutRequest;
import com.microservices.product.service.entity.OrderStatus;
import com.microservices.product.service.entity.Orders;

public interface OrderService {

	Orders placeOrder(CheckoutRequest request, Long buyerId);

	List<Orders> getBuyerOrderById(Long buyerId);

	void cancelorderItemById(Long orderItemId);

	List<Orders> getAllRetailerOrders(long retailerId);

	void updateItemStatus(Long orderItemId, OrderStatus status);

}
