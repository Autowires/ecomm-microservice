package com.microservices.reviews.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.reviews.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
