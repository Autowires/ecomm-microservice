package com.microservices.product.service.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.service.dao.CartItemRepository;
import com.microservices.product.service.dao.CartRepository;
import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.entity.CartItem;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.exception.ResourceNotFoundException;
import com.microservices.product.service.service.BuyerServvice;
import com.microservices.product.service.service.CartService;
import com.microservices.product.service.service.ProductService;

import jakarta.transaction.Transactional;

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
		System.out.println(productId);
		Product product=productService.findProductById(productId);
		System.out.println("the product details are");
		System.out.println(product);
		System.out.println("product details+++++++++++++++++++");
		System.out.println("ProductId"+product.getId());
			CartItem cartItem = cart.getItems()
	                .stream()
	                .filter(item ->item.getProduct().getId().equals(productId))
	                .findFirst().orElse(new CartItem());
		if(cartItem.getCartItemId()==null) {
			System.out.println("inside cartItem==null");
			cartItem.setCart(cart);
			cartItem.setProduct(product);
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
	
		
	@Override
	public Cart getBuyerCartById(Long buyerId) {
		Cart cart=cartRepository.findBybuyerId(buyerId);
		System.out.println("inside the getBuyerCart "+cart);
		return  cart;
	}
	@Transactional
	@Override
	public void clearBuyerCart(Long cartId) {
		System.out.println("control from orderController");
		//finds the cart of the buyer by using the buyerid
		System.out.println("cartId is"+cartId);
		  Cart cart = getCart(cartId);
		  //deletes all the cartitems  associated  with the  cartId of the buyer
		  if(cart!=null) {
			  System.out.println("Cart is not null"); 
		  }
		  cart.getItems().clear();
//		  cartItemRepository.deleteAllByCart(cart);
		 
	       // The clearCart() method on the Cart object is invoked (assuming it resets or clears the state of the cart).
//	        cart.clearCart();
	      //after deleting the cartItems we are deleting the cart
//	        cartRepository.deleteById(cartId);
		
	}
	@Override
	public Cart getCart(Long cartId) {
		 Cart cart = cartRepository.findById(cartId)
	                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
		return cart;
	
	}
	
	

}
