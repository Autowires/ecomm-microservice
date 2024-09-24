package com.microservices.product.service.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.CartItemRepository;
import com.microservices.product.service.dao.CartRepository;
import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.entity.CartItem;
import com.microservices.product.service.exception.ResourceNotFoundException;
import com.microservices.product.service.service.CartItemService;
import com.microservices.product.service.service.CartService;


@Service
public class CartItemserviceImplementation  implements CartItemService{
  
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartRepository cartRepository;
	

	@Override
	public void removeItemFromcart(Long cartId, Long productId) {
		//method is designed to fetch a Cart entity based on the given cartId
		Cart cart=cartService.getCart(cartId);
		//checking product is present inside the cartItem table
		//will return a specific CartItem from the cart that matches the given productId.
		CartItem itemToRemove=getCartItem(cartId,productId);
		cart.removeItem(itemToRemove);
		if(cart.getItems().isEmpty()) {
			cartRepository.deleteById(cartId);
		}else {
			cartRepository.save(cart);	
		}
		 
	}

////will return a specific CartItem from the cart that matches the given productId.
	@Override
	public CartItem getCartItem(Long cartId, Long productId) {
		//getting the cart by the cartId
		Cart cart=cartService.getCart(cartId);
		//checking product is present inside the cart Item
		return cart.getItems().stream().
				filter(item->item.getProductId().equals(productId))
				.findFirst()
				.orElseThrow(()-> new ResourceNotFoundException("product Id is Not found"));			
	}
	@Override
	public CartItem updateItemQuntity(Long cartId, Long productId, Integer quantity) {
		//fetch the cart by id;
		Cart cart=cartService.getCart(cartId);
		// Find the CartItem by product ID and update the quantity
	    Optional<CartItem> updatedItem = cart.getItems().stream()
	        .filter(item -> item.getProductId().equals(productId))
	        .findFirst();
	    updatedItem .ifPresent(item->{
			Integer updateQuantity=item.getQuantity()+quantity;
			
			if(updateQuantity>0) {
				item.setQuantity(updateQuantity);
			}else {
				 // Optionally, remove the item if the quantity reaches 0
				cart.removeItem(item);
//				cartRepository.deleteById(cartId);
				
			}});
//		cart.getItems().stream()
//		.filter(item->item.getProductId().equals(productId))
//		.findFirst().ifPresent(item->{
//			Integer updateQuantity=item.getQuantity()+quantity;
//			
//			if(updateQuantity>0) {
//				item.setQuantity(updateQuantity);
//			}else {
//				 // Optionally, remove the item if the quantity reaches 0
//				cart.removeItem(item);
//			}
//			
//		});
		  cartRepository.save(cart);
		 return  updatedItem.orElse(null);
		
	}	
}
