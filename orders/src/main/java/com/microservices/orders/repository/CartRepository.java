package com.microservices.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.orders.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findBybuyerId(Long buyerId);

}
