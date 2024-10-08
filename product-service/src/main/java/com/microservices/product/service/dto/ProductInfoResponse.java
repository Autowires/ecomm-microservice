package com.microservices.product.service.dto;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.microservices.product.service.entity.Product;
import com.microservices.product.service.entity.ProductReview;

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
	private List<ReviewResponse> reviews;

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
		this.imageUrl=  product.getImageUrl();
		reviews = new LinkedList<>();
	}

	public void addReview(ReviewResponse review) {
		reviews.add(review);
	}

	// Nested DTO for reviews
	@Getter
	@Setter
	public static class ReviewResponse {
		private long id;
		private long customerId;
		private String reviewContent;
		private int rating;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;

		// Constructor mapping ProductReview entity to this response class
		public ReviewResponse(ProductReview review) {
			id = review.getId();
			this.customerId = review.getCustomerId();
			this.reviewContent = review.getReviewContent();
			this.rating = review.getRating();
			this.createdAt = review.getCreatedAt();
			this.updatedAt = review.getUpdatedAt();
		}
	}
}
