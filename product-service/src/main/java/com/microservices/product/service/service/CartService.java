package com.microservices.product.service.service;

import java.util.List;

import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.entity.Customer;

public interface CartService {

	Customer getBuyerById(Long buyerId);

	Cart initializeNewCart(Customer customer);

	Cart addItemToCart(Long cartId, Long productId, Integer quantity);

	List<CartResponse> getBuyerCartById(Long buyerId);

	Cart getCart(Long cartId);

	void clearBuyerCart(Long cartId);

}
