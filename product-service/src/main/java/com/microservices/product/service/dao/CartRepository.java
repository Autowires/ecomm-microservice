package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findBybuyerId(Long buyerId);

}
