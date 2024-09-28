package com.microservices.product.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	void deleteAllByCart(Cart cart);



	

}
