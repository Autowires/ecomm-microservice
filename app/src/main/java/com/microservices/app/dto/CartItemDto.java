package com.microservices.app.dto;



import lombok.Data;

@Data
public class CartItemDto {
	  private Long cartItemId;
	    private int quantity;
	    private double totalPrice;
	    private ProductDto product;
}
