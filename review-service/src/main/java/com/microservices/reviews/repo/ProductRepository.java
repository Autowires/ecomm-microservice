package com.microservices.reviews.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.reviews.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
