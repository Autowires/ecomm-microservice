package com.microservices.app.dto;

import lombok.Data;

@Data
public class ProductDto {
	 private Long productId;
	    private String productName;
	    private String description;
	    private double price;
}
