package com.microservices.customer.service;

import java.util.List;

import com.microservices.customer.dto.WishlistResponse;

public interface CustomerService {

	List<WishlistResponse> getWishlistedItems(long userId);

	void addWishlistItem(long userId, long productId);

	void removeWishlistItem(long userId, long productId);

}
