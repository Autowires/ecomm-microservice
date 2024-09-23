package com.microservices.orders.service;

import com.microservices.orders.entity.CartItem;

public interface CartItemService {



	void removeItemFromcart(Long cartId, Long productId);
	 CartItem getCartItem(Long cartId, Long productId);
    CartItem updateItemQuntity(Long cartId, Long productId, Integer qunatity);
}
