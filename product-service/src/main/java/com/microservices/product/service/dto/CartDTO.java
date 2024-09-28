package com.microservices.product.service.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
@Data
public class CartDTO {
	  private Long cartId;
	    private Long buyerId;
	    private Set<CartItemDTO> items = new HashSet<>();

}
