package com.microservices.product.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.CartItemRepository;
import com.microservices.product.service.dao.CustomerRepository;
import com.microservices.product.service.dao.ProductRepository;
import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.entity.CartItem;
import com.microservices.product.service.entity.Customer;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.CartItemService;

@Service
public class CartItemserviceImplementation implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void removeItemFromcart(Long cartId) {
		cartItemRepository.deleteById(cartId);
	}

	@Override
	public CartItem updateItemQuntity(Long cartId, Long productId, Integer quantity) {
		var item = cartItemRepository.findById(cartId).orElseThrow();
		var available = item.getProduct().getStock();
		item.setQuantity(Math.min(available, quantity));
		return cartItemRepository.save(item);
	}
	
	@Override
	public List<CartResponse> getBuyerCartById(Long buyerId) {
		return cartItemRepository.findCartItemsByCustomerId(buyerId);
	}

	@Override
	public void addItemToCart(Long buyerId, Long productId, Integer quantity) {
		Customer c = customerRepository.findById(buyerId).orElseThrow();
		Product p = productRepository.findById(productId).orElseThrow();
		CartItem ci = new CartItem();
		ci.setProduct(p);
		ci.setCustomer(c);
		ci.setQuantity(quantity);
		cartItemRepository.save(ci);
	}

	@Override
	public void clearBuyerCart(Long buyerId) {
		 cartItemRepository.deleteAllByBuyerId(buyerId);
		
	}
}
