package com.microservices.wishlist.service;

import com.microservices.wishlist.dto.WishlistResponse;
import com.microservices.wishlist.entity.WishList;
import com.microservices.wishlist.exception.CustomWishlistException;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * WishlistService interface to manage wish-listed items for customers.
 * Provides methods to retrieve wish-listed items for a specific user, add a new item to the wishlist,
 * and remove an item from the wishlist.
 */
public interface WishlistService {

    /**
     * Retrieves the wish-listed items for a specific user identified by the provided userId.
     *
     * @param userId the unique identifier of the user
     * @return a list of WishlistResponse objects representing the wishlisted items
     */
    List<WishlistResponse> getWishlistedItems(long userId);

    WishList addWishlistItem(long customerId, long productId) throws CustomWishlistException;

    void removeWishlistItem(long userId, long productId);

}
