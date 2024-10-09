package com.microservices.reviews.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.reviews.entity.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

}
