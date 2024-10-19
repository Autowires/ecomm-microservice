package com.microservices.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customer.dao.CustomerDAO;
import com.microservices.customer.dao.ProductDAo;
import com.microservices.customer.dao.WishlistDAO;
import com.microservices.customer.dto.WishlistResponse;
import com.microservices.customer.entity.Customer;
import com.microservices.customer.entity.Product;
import com.microservices.customer.entity.WishList;
import com.microservices.customer.exception.AddWishlistItemException;
import com.microservices.customer.service.CustomerService;

import jakarta.transaction.Transactional;

/**
 * Implementation of the CustomerService interface. This class contains the logic to manage
 * the customer's wishlist, including adding, retrieving, and removing products from the wishlist.
 * It interacts with the database through DAOs (Data Access Objects) and handles transactions.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    // DAO for interacting with the wishlist table
    @Autowired
    private WishlistDAO dao;

    // DAO for interacting with the customer table
    @Autowired
    private CustomerDAO customerDAO;

    // DAO for interacting with the product table
    @Autowired
    private ProductDAo productDAO;

    /**
     * Retrieves the list of products in a customer's wishlist.
     *
     * @param userId The ID of the customer whose wishlist is being retrieved.
     * @return A list of WishlistResponse objects representing the customer's wishlist items.
     */
    @Override
    @Transactional
    public List<WishlistResponse> getWishlistedItems(long userId) {
        return dao.findWishlistsByCustomerId(userId);
    }

    /**
     * Adds a product to the customer's wishlist.
     * 
     * Steps:
     * - Fetches the customer and product from the database.
     * - Checks if the product is already in the customer's wishlist.
     * - If not, creates a new wishlist entry and saves it to the database.
     * 
     * @param userId The ID of the customer.
     * @param productId The ID of the product to be added to the wishlist.
     * @throws AddWishlistItemException if the customer or product is not found, or if the product is already in the wishlist.
     */
    @Override
    @Transactional
    public void addWishlistItem(long userId, long productId) {
        Customer customer = customerDAO.findById(userId)
                .orElseThrow(() -> new AddWishlistItemException("Customer not found"));

        Product product = productDAO.findById(productId)
                .orElseThrow(() -> new AddWishlistItemException("Product not found"));

        Optional<WishList> existingWishList = dao.findByCustomerIdAndProductId(userId, productId);
        if (existingWishList.isPresent()) {
            throw new AddWishlistItemException("Product is already in the wishlist");
        }

        WishList wishList = new WishList();
        wishList.setCustomer(customer);
        wishList.setProduct(product);
        dao.save(wishList);
    }

    /**
     * Removes a product from the customer's wishlist.
     * 
     * Steps:
     * - Finds all wishlist entries for the specified user and product.
     * - If found, deletes all related entries.
     * - If no entries are found, throws an exception.
     * 
     * @param userId The ID of the customer.
     * @param productId The ID of the product to be removed from the wishlist.
     * @throws RuntimeException if no wishlist entry is found for the given customer and product.
     */
    @Override
    @Transactional
    public void removeWishlistItem(long userId, long productId) {
        List<WishList> wishLists = dao.findAllByCustomerIdAndProductId(userId, productId);

        if (wishLists.isEmpty()) {
            throw new RuntimeException("No wishlist entry found for userId: " + userId + " and productId: " + productId);
        }

        wishLists.forEach(wishlist -> dao.delete(wishlist));
    }
}
