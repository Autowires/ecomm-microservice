package com.microservices.product.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ApiResponse;
import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.exception.ResourceNotFoundException;
import com.microservices.product.service.service.CartItemService;

@RestController
@RequestMapping("/customers/{buyerId}/cart")
public class CartController {

	@Autowired
	private CartItemService cartItemService;

	@PostMapping
	public ResponseEntity<String> addProductToCart(@PathVariable Long buyerId, @RequestParam Long productId,
			@RequestParam(defaultValue = "1") Integer quantity) {
		cartItemService.addItemToCart(buyerId, productId, quantity);
		return ResponseEntity.ok("Added to cart");

	}

	@GetMapping
	public ResponseEntity<List<CartResponse>> getBuyerCartById(@PathVariable Long buyerId) {
		return ResponseEntity.ok(cartItemService.getBuyerCartById(buyerId));
	}

	@PutMapping("/{cartId}")
	public ResponseEntity<String> updateItemQunatity(@PathVariable Long cartId, @RequestParam Long productId,
			@RequestParam Integer quantity) {
		try {
			cartItemService.updateItemQuntity(cartId, productId, quantity);
			return ResponseEntity.ok("Cart updated successfully");
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<ApiResponse> clearBuyerCart(@PathVariable Long cartId) {
		try {
			cartItemService.removeItemFromcart(cartId);
			return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

}
