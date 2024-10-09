package com.microservices.reviews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.reviews.dto.ProductReviewResponse;
import com.microservices.reviews.entity.ProductReview;
import com.microservices.reviews.repo.ProductReviewRepository;
import com.microservices.reviews.service.ProductReviewService;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private ProductReviewRepository productReviewRepository;

	@Override
	public List<ProductReviewResponse> getReviewsByCustomerId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProductReview(Long userId, long productId, String reviewContent, int rating) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReview(long reviewId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductReview> getProductReviews(long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
