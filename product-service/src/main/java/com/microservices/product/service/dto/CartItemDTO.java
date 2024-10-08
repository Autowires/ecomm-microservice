package com.microservices.product.service.dto;

import lombok.Data;

@Data
public class CartItemDTO {
	    private Long cartItemId;
	    private int quantity;
//	    private double totalPrice;
	    private ProductDTO product;

}
