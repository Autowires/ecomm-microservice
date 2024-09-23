package com.microservices.orders.service;

import com.microservices.orders.entity.Buyer;
import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.Product;

public interface CartService {

	Buyer getBuyerById(Long buyerId);

	Cart initializeNewCart(Buyer buyer);

	Cart addItemToCart(Long cartId, Long productId, Integer quantity);

	Cart getBuyerCartById(Long buyerId);
    
	 Cart getCart(Long cartId);
  
	void clearBuyerCart(Long cartId);

	

	

	

}
