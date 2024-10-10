package com.microservices.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductReviewResponse {
	private long reviewId;
	private long productId;
	private String productName;
	private String reviewContent;
	private int rating;
}