package com.microservices.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.customer.entity.Product;

public interface ProductDAo extends JpaRepository<Product, Long> {

}
