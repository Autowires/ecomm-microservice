package com.microservices.wishlist.controller;

import com.microservices.wishlist.dto.WishlistResponse;
import com.microservices.wishlist.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WishlistController handles the RESTful API requests related to customer
 * wishlists. It provides endpoints to view, add, and remove products from a
 * customer's wishlist.
 * Base URL: /customers/{userId}/wishlist
 */
@RestController
@RequestMapping("/customers/{userId}/wishlist")
public class WishlistController {

	// Logger instance for logging important information related to wishlist
	// operations.
	Logger logger = LoggerFactory.getLogger(WishlistController.class);

	@Autowired
	private WishlistService service;

	/**
	 * Retrieves the wishlist for a specific user.
	 * 
	 * @param userId The ID of the user whose wishlist is to be retrieved.
	 * @return A list of WishlistResponse objects representing the products in the
	 *         user's wishlist.
	 * 
	 *         Example request: GET localhost:8084/customers/3/wishlist
	 */
	@GetMapping
	public List<WishlistResponse> wishlistView(@PathVariable Long userId) {
		return service.getWishlistedItems(userId);
	}

	/**
	 * Adds a product to the user's wishlist.
	 * 
	 * @param userId    The ID of the user.
	 * @param productId The ID of the product to be added to the wishlist.
	 * @return A ResponseEntity containing a success message with HTTP status 201
	 *         (Created).
	 * 
	 *         Example request: POST localhost:8084/customers/3/wishlist?productId=3
	 */
	@PostMapping
	public ResponseEntity<String> addProduct(@PathVariable Long userId, @RequestParam Long productId) {
		service.addWishlistItem(userId, productId);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product added to wishlist");
	}

	/**
	 * Removes a product from the user's wishlist.
	 * 
	 * @param userId    The ID of the user.
	 * @param productId The ID of the product to be removed from the wishlist.
	 * @return A ResponseEntity containing a success message with HTTP status 200
	 *         (OK), or an error message if an exception occurs.
	 * 
	 *         Example request: DELETE
	 *         localhost:8084/customers/3/wishlist?productId=3
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteProduct(@PathVariable Long userId, @RequestParam Long productId) {
		service.removeWishlistItem(userId, productId);
		return ResponseEntity.ok("Item removed successfully");
	}
}
