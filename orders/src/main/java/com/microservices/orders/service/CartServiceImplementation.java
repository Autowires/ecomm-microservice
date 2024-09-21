package com.microservices.orders.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.orders.dao.CartDao;
import com.microservices.orders.entity.Buyer;
import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.CartItem;
import com.microservices.orders.entity.Product;
import com.microservices.orders.exception.ResourceNotFoundException;
import com.microservices.orders.repository.CartRepository;

@Service
public class CartServiceImplementation implements CartService {

	 
	@Autowired
	private CartRepository cartRepository ;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BuyerServvice buyerServvice;
	
	@Override
	public Buyer getBuyerById(Long buyerId) {
	Buyer buyer=buyerServvice.getBuyerById(buyerId);
		return buyer;
	}
	@Override
	public Cart initializeNewCart(Buyer buyer) {
		return Optional.ofNullable(getBuyerCartById(buyer.getBuyerId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setBuyerId(buyer.getBuyerId());
                    return cartRepository.save(cart);
                });
//		Cart cart=getBuyerCartById(buyer.getBuyerId());
//		if(cart==null) {
//			Cart newCart=new Cart();
//			newCart.setBuyerId(buyer.getBuyerId());
//			 return cartRepository.save(cart);
//		}
//		return cart;
//				
//		
		
	}

	@Override
	public Cart addItemToCart(Long cartId, Long productId, Integer quantity) {
		Cart cart=getCart(cartId);
		Product product=productService.findProductById(productId);
		System.out.println("product details+++++++++++++++++++");
		System.out.println("ProductId"+product.getProductId());
			CartItem cartItem = cart.getItems()
	                .stream()
	                .filter(item -> item.getProductId().equals(productId))
	                .findFirst().orElse(new CartItem());
		if(cartItem.getProductId()==null) {
			System.out.println("inside cartItem==null");
			cartItem.setCart(cart);
			cartItem.setProductId(productId);
			cartItem.setQuantity(quantity);
		}else {
			cartItem.setQuantity(cartItem.getQuantity()+quantity);
		}
		System.out.println(cartItem.getQuantity());
		 cart.addItem(cartItem);
	        
		 cartItemRepository.save(cartItem);
		Cart additem= cartRepository.save(cart);
		return additem;
	}   
		 
	
	private Cart getCart(Long cartId) {
		 Cart cart = cartRepository.findById(cartId)
	                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
		return cart;
	}
	@Override
	public Cart getBuyerCartById(Long buyerId) {
		Cart cart=cartRepository.findBybuyerId(buyerId);
		return  cart;
	}
	
	
	
	

}
