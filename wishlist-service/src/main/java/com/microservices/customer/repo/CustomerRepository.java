package com.microservices.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
