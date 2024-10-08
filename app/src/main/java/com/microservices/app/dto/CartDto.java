package com.microservices.app.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;


@Data
public class CartDto {
	 private Long cartId;
	    private Long buyerId;
	    private Set<CartItemDto> items = new HashSet<>();
	

}
