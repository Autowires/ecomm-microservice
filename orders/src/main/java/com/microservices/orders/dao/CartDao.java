package com.microservices.orders.dao;

import com.microservices.orders.entity.Buyer;
import com.microservices.orders.entity.Cart;

public interface CartDao {

	Buyer getBuyerById(Long buyerId);

	Cart initializeNewCart(Buyer buyer);

	Cart addItemTtoCart(Long cartId, Long productId, Integer quantity);

	

	


}
