package com.microservices.product.service.controller;



import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ApiResponse;
import com.microservices.product.service.entity.OrderStatus;
import com.microservices.product.service.service.OrderService;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {
	@Autowired
	private OrderService orderService;
	
	private Logger logger = LogManager.getLogger();

	@DeleteMapping("/{orderItemId}/cancel")
	public ResponseEntity<ApiResponse> cancelOrderById(@PathVariable Long orderItemId) {
		try {
			orderService.cancelorderItemById(orderItemId);
			return ResponseEntity.ok(new ApiResponse("Order cancelled sucessFully", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse(e.getMessage(), null)));
		}

	}

	@PutMapping("/{orderItemId}/updateStatus")
	public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderItemId,
			@RequestParam OrderStatus status) {
		try {
			orderService.updateItemStatus(orderItemId, status);
			return ResponseEntity.ok(new ApiResponse("Order status updated sucessFully", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse(e.getMessage(), null)));
		}

	}
}
