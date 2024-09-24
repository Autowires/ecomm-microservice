package com.microservices.product.service.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long OrderItemId;
	    private Integer quantity;
	    private BigDecimal price;


	    @ManyToOne
	    @JoinColumn(name = "order_id")
	    private Orders order;

	  private Long productId;

	    public OrderItem(Orders order, Long productId, Integer quantity, BigDecimal price) {
	        this.order = order;
	        this.productId = productId;
	        this.quantity = quantity;
	        this.price = price;

	    }
}
