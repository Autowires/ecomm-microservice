package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.product.service.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
