package com.microservices.reviews.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.reviews.dto.ProductReviewResponse;
import com.microservices.reviews.entity.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

	@Query("SELECT new com.microservices.reviews.dto.ProductReviewResponse(pr.id,pr.product.id, pr.product.title, pr.reviewContent, pr.rating) "
			+ "FROM ProductReview pr WHERE pr.customer.id = :customerId")
	List<ProductReviewResponse> findReviewsByCustomerId(@Param("customerId") long customerId);

	List<ProductReview> findByProductId(long productId);
}
