package com.microservices.product.service.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long OrderItemId;
	    private Integer quantity;
	    private double price;

	    @JsonBackReference 
	    @ManyToOne
	    @JoinColumn(name = "order_id")
	    private Orders order;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    public OrderItem(Orders order, Product product, Integer quantity, double price) {
	        this.order = order;
	        this.product = product;
	        this.quantity = quantity;
	        this.price = price;

	    }
}
