package com.microservices.product.service.controller;

import com.microservices.product.service.dto.ApiResponse;
import com.microservices.product.service.dto.CartDTO;
import com.microservices.product.service.dto.CartItemDTO;
import com.microservices.product.service.dto.ProductDTO;
import com.microservices.product.service.entity.Cart;
import com.microservices.product.service.entity.Customer;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.exception.ResourceNotFoundException;
import com.microservices.product.service.service.BuyerServvice;
import com.microservices.product.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private BuyerServvice buyerService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addtocart/{buyerId}")
	public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long buyerId,@RequestParam("productId") Long  productId,@RequestParam(required = false,defaultValue = "1" ) Integer quantity ){
		
		System.out.println("hello");
		//fetching buyer details by buyerId;
		System.out.println(buyerId);
		System.out.println(productId);
		Customer customer =buyerService.getBuyerById(buyerId);
		Cart cart = cartService.initializeNewCart(customer);
		
		 System.out.println("back to controller and cartId "+cart.getCartId());
	    Cart addItem=cartService.addItemToCart(cart.getCartId(),productId,quantity);
	    CartDTO cartDto=convertToCartDTO(addItem);
		System.out.println(customer.getUsername());
		return ResponseEntity.ok(cartDto);
				
	}
	
	@GetMapping("/getbuyercart/{buyerId}")
	public ResponseEntity<CartDTO> getBuyerCartById(@PathVariable Long buyerId){
		 Cart cart = cartService.getBuyerCartById(buyerId);
		 CartDTO cartDto=convertToCartDTO(cart);
		 return ResponseEntity.ok(cartDto);
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
	private CartDTO convertToCartDTO(Cart addItem) {
		CartDTO cartDto=new CartDTO();
		cartDto.setCartId(addItem.getCartId());
//		cartDto.setBuyerId(addItem.getBuyerId());
		cartDto.setBuyerId(addItem.getBuyerId());
		Set<CartItemDTO> cartItem=addItem.getItems().stream().map(item->{
			 CartItemDTO cartItemDTO = new CartItemDTO();
			 cartItemDTO.setCartItemId(item.getCartItemId());
			 cartItemDTO.setQuantity(item.getQuantity());
			    Product product = item.getProduct();
		        ProductDTO productDTO = new ProductDTO();

			    productDTO.setProductId(product.getId());
		        productDTO.setProductName(product.getTitle());
		        productDTO.setDescription(product.getDescription());
		        productDTO.setPrice(product.getPrice());
		        cartItemDTO.setProduct(productDTO);
		        return cartItemDTO;
		}
		).collect(Collectors.toSet());
		cartDto.setItems(cartItem);
		    return cartDto;
	}

}
