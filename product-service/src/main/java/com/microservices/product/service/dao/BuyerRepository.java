package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.product.service.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
