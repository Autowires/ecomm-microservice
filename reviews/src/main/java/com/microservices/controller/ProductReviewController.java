package com.microservices.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.microservices.dto.CurrentUser;
import com.microservices.entity.ProductReview;
import com.microservices.service.ProductReviewService;



@RestController
@RequestMapping("/reviews")
public class ProductReviewController {

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping
	public ResponseEntity<List<ProductReview>> listReviewsByProduct(@SessionAttribute CurrentUser user) {
		List<ProductReview> reviews = productReviewService.getReviewsByCustomerId(user.getUserId());
		return ResponseEntity.ok(reviews);
	}

	@PostMapping
	public ResponseEntity<String> createReview(@SessionAttribute CurrentUser user, @RequestParam long productId,
			@RequestParam String reviewContent, @RequestParam int rating) {
		productReviewService.saveProductReview(user.getUserId(), productId, reviewContent, rating);
		return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteReview(@SessionAttribute CurrentUser user, @RequestParam long reviewId) {
		productReviewService.deleteReview(reviewId);
		return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
	}
}
