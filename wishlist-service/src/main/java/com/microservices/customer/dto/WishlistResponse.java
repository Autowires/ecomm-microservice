package com.microservices.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WishlistResponse {
	private long id;
	private long productId;
	private String productName;
	private double price;
}
