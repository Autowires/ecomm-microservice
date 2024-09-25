package com.microservices.product.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ApiResponse;
import com.microservices.product.service.dto.OrderDTO;
import com.microservices.product.service.entity.Orders;
import com.microservices.product.service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired	
   private OrderService orderService;
    
    @PostMapping("/placeorder/{buyerId}")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody Orders order, @PathVariable Long buyerId){
     try {
    	 Orders ordered=orderService.placeOrder(order,buyerId);
    	 return ResponseEntity.ok(new ApiResponse("ordered place sucessFuly", null));
     }catch(Exception e ) {
    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body((new ApiResponse(e.getMessage(),  null)));
     }
    	

    	
    }
}
