package com.microservices.product.service.service;

import com.microservices.product.service.entity.CartItem;

public interface CartItemService {



	void removeItemFromcart(Long cartId, Long productId);
	 CartItem getCartItem(Long cartId, Long productId);
    CartItem updateItemQuntity(Long cartId, Long productId, Integer qunatity);
}
