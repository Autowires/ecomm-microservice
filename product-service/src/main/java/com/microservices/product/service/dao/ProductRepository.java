package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
