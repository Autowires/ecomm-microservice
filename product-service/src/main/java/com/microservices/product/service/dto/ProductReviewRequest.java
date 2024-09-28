package com.microservices.product.service.dto;

import lombok.Data;

@Data
public class ProductReviewRequest {
	private long customerId;
	private long productId;
	private String reviewContent;
	private int rating;
}
