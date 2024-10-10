package com.microservices.product.service.dto;

import java.time.LocalDateTime;

import com.microservices.product.service.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfoResponse {

	private Long id;
	private String title;
	private String description;
	private long retailerId;
	private double price;
	private String category;
	private Integer stock;
	private LocalDateTime registeredAt;
	private LocalDateTime modifiedAt;
	private String imageUrl;

	// Constructor mapping the Product entity to this response class
	public ProductInfoResponse(Product product) {
		this.id = product.getId();
		this.title = product.getTitle();
		this.description = product.getDescription();
		this.retailerId = product.getRetailerId();
		this.price = product.getPrice();
		this.category = product.getCategory();
		this.stock = product.getStock();
		this.registeredAt = product.getRegisteredAt();
		this.modifiedAt = product.getModifiedAt();
		this.imageUrl = product.getImageUrl();
	}

}
