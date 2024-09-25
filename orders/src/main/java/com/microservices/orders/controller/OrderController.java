package com.microservices.orders.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.orders.dto.OrderDTO;
import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.Orders;
import com.microservices.orders.service.CartService;
import com.microservices.orders.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
   private OrderService OrderService;
    
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody Orders order,@PathVariable Long buyerId){
	Orders order=OrderService.placeOrder(order,buyerId);
		
	}
}
