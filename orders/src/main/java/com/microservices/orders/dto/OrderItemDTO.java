package com.microservices.orders.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
	    private Long productId;       // Product associated with this order item
	    private Integer quantity;     // Quantity of the product ordered
	    private BigDecimal price;     // Price of the product for this order item
	    private String productName;   //

}
