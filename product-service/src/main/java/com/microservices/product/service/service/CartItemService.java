package com.microservices.product.service.service;

import java.util.List;

import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.entity.CartItem;

public interface CartItemService {

	void removeItemFromcart(Long cartItemId);

	CartItem updateItemQuntity(Long cartItemId, Long productId, Integer qunatity);

	List<CartResponse> getBuyerCartById(Long buyerId);

	void addItemToCart(Long buyerId, Long productId, Integer quantity);

	void clearBuyerCart(Long buyerId);

}
