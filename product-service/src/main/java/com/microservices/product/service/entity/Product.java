package com.microservices.product.service.entity;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable = false)
	private String title;
	private String description;
	@Column(nullable = false)
	private long retailerId;
	private double price;
	@Column(nullable = false)
	private String category;
	private Integer stock;
	
	@CreationTimestamp
	private LocalDateTime registeredAt;
	@UpdateTimestamp
	private LocalDateTime modifiedAt;
	
	  @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
	  @JsonIgnore
	  private List<CartItem> cartItems = new ArrayList<>();
	
}
