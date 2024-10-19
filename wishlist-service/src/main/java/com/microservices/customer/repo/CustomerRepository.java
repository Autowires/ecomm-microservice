package com.microservices.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.customer.entity.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Long> {

}
