package com.microservices.orders.controller;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.orders.entity.CartItem;
import com.microservices.orders.exception.ResourceNotFoundException;
import com.microservices.orders.response.ApiResponse;
import com.microservices.orders.service.CartItemService;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
 
 @Autowired	
 private CartItemService cartItemService;

	
@DeleteMapping("/removecartItem/{cartId}/product/{productId}")	
public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,@PathVariable Long  productId){	
	
	try {
		cartItemService.removeItemFromcart(cartId,productId);
		return ResponseEntity.ok(new ApiResponse("cartItem deleted", null));
	}catch(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body((new ApiResponse(e.getMessage(),  null)));
	}
}

@PutMapping("/updatecartitem/cart/{cartId}/productId/{productId}/qunatity/{qunatity}")
public ResponseEntity<CartItem> updateItemQunatity(@PathVariable Long cartId,@PathVariable Long productId,@PathVariable Integer qunatity){
	try {
	CartItem cartItem=cartItemService.updateItemQuntity(cartId,productId,qunatity);
	return ResponseEntity.ok(cartItem);
	}catch(Exception e) {
      return ResponseEntity.ok(null);
	}
}

	
}
