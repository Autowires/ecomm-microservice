package com.microservices.app.dto;

import lombok.Data;

@Data
public class ProductDto {
	    private Long productId;
	    private String productName;  
		private String title;
		private String description;
		private String  category;
		private double price;
		private Integer stock;
		private String imageUrl;
}
