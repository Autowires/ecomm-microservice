package com.microservices.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponse {
	private long id;
	private long productId;
	private String productName;
	private double price;
	private int quantity;
	private int stock;
	private String imageUrl;
}
