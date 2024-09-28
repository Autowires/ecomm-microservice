package com.microservices.product.service.service;

import com.microservices.product.service.entity.Customer;
import com.microservices.product.service.entity.Cart;

public interface CartService {

	Customer getBuyerById(Long buyerId);

	Cart initializeNewCart(Customer customer);

	Cart addItemToCart(Long cartId, Long productId, Integer quantity);

	Cart getBuyerCartById(Long buyerId);
    
	 Cart getCart(Long cartId);
  
	void clearBuyerCart(Long cartId);

	

	

	

}
