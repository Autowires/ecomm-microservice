package com.microservices.product.service.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;
	private int quantity;
	@JsonBackReference //// Prevents recursion from CartItem back to Cart
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
//    private Long productId;
	
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
