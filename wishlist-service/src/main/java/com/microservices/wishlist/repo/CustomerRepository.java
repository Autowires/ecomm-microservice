package com.microservices.wishlist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.wishlist.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
