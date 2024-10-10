package com.microservices.reviews.service;

import java.util.List;

import com.microservices.reviews.dto.ProductReviewResponse;
import com.microservices.reviews.entity.ProductReview;

public interface ProductReviewService {

	List<ProductReviewResponse> getReviewsByCustomerId(Long userId);

	void saveProductReview(Long userId, long productId, String reviewContent, int rating);

	void deleteReview(long reviewId);

	List<ProductReview> getProductReviews(long productId);

}
