package com.microservices.product.service.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
//	    private Long productId;
	    private String productName;
//	    private String productBrand;
	    private int quantity;
	    private double price;
	    private String discription;
}
