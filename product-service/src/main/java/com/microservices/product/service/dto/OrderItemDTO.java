package com.microservices.product.service.dto;

import com.microservices.product.service.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderItemDTO {
//	    private Long productId;
	private String productName;
//	    private String productBrand;
	private long orderItemId;
	private int quantity;
	private double price;
	private String discription;
	private OrderStatus orderStatus; // Add orderStatus field
}
