package com.microservices.orders.controller;

import org.hibernate.annotations.NotFound;
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

import com.microservices.orders.entity.Buyer;
import com.microservices.orders.entity.Cart;
import com.microservices.orders.entity.Product;
import com.microservices.orders.exception.ResourceNotFoundException;
import com.microservices.orders.response.ApiResponse;
import com.microservices.orders.service.BuyerServvice;
import com.microservices.orders.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private BuyerServvice buyerService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addtocart/{buyerId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable Long buyerId,@RequestParam Long productId,@RequestParam(required = false,defaultValue = "1" ) Integer quantity ){
		System.out.println("hello");
		//fetching buyer details by buyerId;
		System.out.println(buyerId);
		Buyer buyer=buyerService.getBuyerById(buyerId);
		Cart cart = cartService.initializeNewCart(buyer);
		 System.out.println("back to controller and cartId "+cart.getCartId());
	    Cart addItem=cartService.addItemToCart(cart.getCartId(),productId,quantity);
	   
		System.out.println(buyer.getBuyerName());
		return ResponseEntity.ok(addItem);
				
	}
	@GetMapping("/getbuyercart/{buyerId}")
	public ResponseEntity<Cart> getBuyerCartById(@PathVariable Long buyerId){
		 Cart cart = cartService.getBuyerCartById(buyerId);
		 return ResponseEntity.ok(cart);
	}
	
	@DeleteMapping("deletebuyercart/{cartId}")
  public ResponseEntity<ApiResponse> clearBuyerCart(@PathVariable Long cartId){
	  try {
		  cartService.clearBuyerCart(cartId);
		  return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
	  }catch(ResourceNotFoundException e) {
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		  
	  }
  }

}
