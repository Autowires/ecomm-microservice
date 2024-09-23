package com.microservices.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	void deleteAllByCart(Cart cart);

//	void deleteAllByCartId(Long cartId);

	

}
