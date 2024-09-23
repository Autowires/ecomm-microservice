package com.microservices.authentication.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.authentication.entity.Retailer;

public interface RetailerRepository extends JpaRepository<Retailer, Long> {

}
