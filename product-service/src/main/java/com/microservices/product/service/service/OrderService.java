package com.microservices.product.service.service;

import java.util.List;

import com.microservices.product.service.dto.OrderDTO;
import com.microservices.product.service.entity.Orders;

public interface OrderService {

	Orders placeOrder(Orders order, Long buyerId);

	List<Orders> getBuyerOrderById(Long buyerId);

//	OrderDTO convertToDto(Orders order);

}
