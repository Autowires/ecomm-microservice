package com.microservices.product.service.service;

import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.entity.Cart;

public interface CartService {

	Buyer getBuyerById(Long buyerId);

	Cart initializeNewCart(Buyer buyer);

	Cart addItemToCart(Long cartId, Long productId, Integer quantity);

	Cart getBuyerCartById(Long buyerId);
    
	 Cart getCart(Long cartId);
  
	void clearBuyerCart(Long cartId);

	

	

	

}
