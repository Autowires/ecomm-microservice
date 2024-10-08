package com.microservices.product.service.dto;

import lombok.Data;

@Data
public class ProductDTO {
	    private Long productId;
	    private String productName;
	    private String description;
	    private double price;
		private String imageUrl;
}
