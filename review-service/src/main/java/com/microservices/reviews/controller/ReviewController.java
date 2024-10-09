package com.microservices.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.reviews.entity.ProductReview;
import com.microservices.reviews.service.ProductReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ProductReviewService service;

	@GetMapping
	public List<ProductReview> product(@RequestParam long productId) {
		return service.getProductReviews(productId);
	}
}
