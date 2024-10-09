package com.microservices.reviews.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product_reviews")
public class ProductReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
	private long id;

	@JoinColumn(nullable = false)
	@ManyToOne
	private Customer customer;

	@Column(nullable = false)
	private long productId;

	@Column(nullable = false)
	private String reviewContent;

	@Column(nullable = false)
	private int rating;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
