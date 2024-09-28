package com.microservices.product.service.controller;

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

import com.microservices.product.service.dto.ApiResponse;
import com.microservices.product.service.entity.Buyer;
import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.exception.ResourceNotFoundException;
import com.microservices.product.service.service.BuyerServvice;
import com.microservices.product.service.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private BuyerServvice buyerService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addtocart/{buyerId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable Long buyerId,@RequestParam("productId") Long  productId,@RequestParam(required = false,defaultValue = "1" ) Integer quantity ){
		System.out.println("hello");
		//fetching buyer details by buyerId;
		System.out.println(buyerId);
		System.out.println(productId);
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
