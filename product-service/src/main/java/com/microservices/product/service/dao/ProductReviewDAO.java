package com.microservices.product.service.dao;

import java.util.List;

import com.microservices.product.service.entity.ProductReview;

public interface ProductReviewDAO {

	List<ProductReview> findByProductId(long productId);

	List<ProductReview> getCustomerReviews(long userId);

	void addCustomerReview(long userId, long productId, String reviewContent, int rating);

	void deleteReview(long id);
}