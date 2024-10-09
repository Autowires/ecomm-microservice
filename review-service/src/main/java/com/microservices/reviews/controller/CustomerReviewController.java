package com.microservices.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.reviews.dto.ProductReviewRequest;
import com.microservices.reviews.dto.ProductReviewResponse;
import com.microservices.reviews.service.ProductReviewService;

@RestController
@RequestMapping("/customers/{userId}/reviews")
public class CustomerReviewController {

	@Autowired
	private ProductReviewService service;

	@GetMapping
	public ResponseEntity<List<ProductReviewResponse>> listReviewsByCustomer(@PathVariable Long userId) {
		var reviews = service.getReviewsByCustomerId(userId);
		return ResponseEntity.ok(reviews);
	}

	@PostMapping
	public ResponseEntity<String> createReview(@PathVariable Long userId, @RequestBody ProductReviewRequest request) {
		service.saveProductReview(userId, request.getProductId(), request.getReviewContent(), request.getRating());
		return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable long reviewId) {
		service.deleteReview(reviewId);
		return ResponseEntity.ok("Review deleted successfully");
	}
}
