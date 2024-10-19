package com.microservices.wishlist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.wishlist.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
