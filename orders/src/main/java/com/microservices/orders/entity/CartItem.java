package com.microservices.orders.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;

	private int quantity;


	@JsonBackReference //// Prevents recursion from CartItem back to Cart
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	
private Long productId;
	
	

}
