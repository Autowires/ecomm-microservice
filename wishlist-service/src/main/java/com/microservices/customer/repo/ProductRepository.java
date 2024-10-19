package com.microservices.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.customer.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
