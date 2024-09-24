package com.microservices.product.service.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ProductReviewRequest;
import com.microservices.product.service.entity.ProductReview;
import com.microservices.product.service.service.ProductReviewService;

@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping
	public ResponseEntity<List<ProductReview>> listReviewsByProduct(@RequestParam long custommerId) {
		List<ProductReview> reviews = productReviewService.getReviewsByCustomerId(custommerId);
		return ResponseEntity.ok(reviews);
	}

	@PostMapping
	public ResponseEntity<String> createReview(@RequestBody ProductReviewRequest request) {
		productReviewService.saveProductReview(request.getCustomerId(), request.getProductId(),
				request.getReviewContent(), request.getRating());
		return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteReview(@PathVariable long reviewId) {
		productReviewService.deleteReview(reviewId);
		return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
	}
}
