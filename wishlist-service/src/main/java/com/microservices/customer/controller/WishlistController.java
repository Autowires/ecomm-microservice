package com.microservices.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customer.dto.WishlistResponse;
import com.microservices.customer.service.CustomerService;

/**
 * WishlistController handles the RESTful API requests related to customer wishlists.
 * It provides endpoints to view, add, and remove products from a customer's wishlist.
 * 
 * Base URL: /customers/{userId}/wishlist
 */
@RestController
@RequestMapping("/customers/{userId}/wishlist")
public class WishlistController {
    
    // Logger instance for logging important information related to wishlist operations.
    Logger logger = LoggerFactory.getLogger(WishlistController.class);

    @Autowired
    private CustomerService service;

    /**
     * Retrieves the wishlist for a specific user.
     * 
     * @param userId The ID of the user whose wishlist is to be retrieved.
     * @return A list of WishlistResponse objects representing the products in the user's wishlist.
     * 
     * Example request:
     * GET localhost:8084/customers/3/wishlist
     */
    @GetMapping
    public List<WishlistResponse> wishlistView(@PathVariable Long userId) {
        return service.getWishlistedItems(userId);
    }

    /**
     * Adds a product to the user's wishlist.
     * 
     * @param userId The ID of the user.
     * @param productId The ID of the product to be added to the wishlist.
     * @return A ResponseEntity containing a success message with HTTP status 201 (Created).
     * 
     * Example request:
     * POST localhost:8084/customers/3/wishlist?productId=3
     */
    @PostMapping
    public ResponseEntity<String> addProduct(@PathVariable Long userId, @RequestParam Long productId) {
        service.addWishlistItem(userId, productId);
        logger.info("The product with ID {} is already added to wishlist", productId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to wishlist");
    }

    /**
     * Removes a product from the user's wishlist.
     * 
     * @param userId The ID of the user.
     * @param productId The ID of the product to be removed from the wishlist.
     * @return A ResponseEntity containing a success message with HTTP status 200 (OK), or an error message if an exception occurs.
     * 
     * Example request:
     * DELETE localhost:8084/customers/3/wishlist?productId=3
     */
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@PathVariable Long userId, @RequestParam Long productId) {
        try {
            service.removeWishlistItem(userId, productId);
            return ResponseEntity.ok("All wishlist entries removed for userId: " + userId + " and productId: " + productId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
