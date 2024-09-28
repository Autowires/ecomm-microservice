package com.microservices.product.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ApiResponse;
import com.microservices.product.service.dto.OrderDTO;
import com.microservices.product.service.dto.OrderItemDTO;
import com.microservices.product.service.entity.OrderItem;
import com.microservices.product.service.entity.Orders;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.OrderService;
import com.microservices.product.service.dto.*;
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired	
   private OrderService orderService;
    
    @PostMapping("/placeorder/{buyerId}")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody Orders order, @PathVariable Long buyerId){
     try {
    	 Orders ordered=orderService.placeOrder(order,buyerId);
    	 System.out.println("placeOrder"+ordered);
    	 return ResponseEntity.ok(new ApiResponse("ordered place sucessFuly", null));
     }catch(Exception e ) {
    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse(e.getMessage(),  null)));
     }

    	
    }
    @GetMapping("/buyerid/{buyerId}")   	
    public ResponseEntity<List<OrderDTO>> getBuyerOrderById(@PathVariable Long buyerId){
    	List<Orders> orders=orderService.getBuyerOrderById(buyerId);
    	List<OrderDTO> orderDto=createOrderDto(orders);
    	return ResponseEntity.ok(orderDto);
    }
    
//   public ResponseEntity<List<OrderDTO>> PlacedOrders(@PathVariable Long buyerId){
//	   
//   }

//    @DeleteMapping("/deleteorder/{buyerId}")
//    public ResponseEntity<ApiResponse> cancleOrder(buyerId){
//    	List<Orders> orders=orderService.getBuyerOrderById(buyerId);
//    }
//    
    
    
	private List<OrderDTO> createOrderDto(List<Orders> orders) {

		ArrayList<OrderDTO> orderedItemsList = new ArrayList<>();
	    
	    for (Orders order : orders) {
	        OrderDTO orderDto = new OrderDTO();
	        orderDto.setOrderId(order.getOrderId());
	        orderDto.setOrderDate(order.getOrderDate());
	        orderDto.setDeliveryDate(order.getDeliveryDate());
	        orderDto.setTotalAmount(order.getTotalAmount());
            orderDto.setBuyername(order.getBuyername()) ;
            orderDto.setOrderStatus(order.getOrderStatus());
            orderDto.setAddress(order.getAddress());
            orderDto.setPaymentType(order.getPaymentType());
            System.out.println(order.getOrderStatus());
	        // Convert OrderItems to OrderItemDTO
	        List<OrderItemDTO> orderItemDtos = new ArrayList<>();
	        
	        for (OrderItem orderItem : order.getOrderItems()) {
	            OrderItemDTO orderItemDto = new OrderItemDTO();
//	            orderItemDto.set(orderItem.getOrderItemId());
	            orderItemDto.setQuantity(orderItem.getQuantity());
	            orderItemDto.setPrice(orderItem.getPrice());
	            
	            // Set product details
	            Product product = orderItem.getProduct();
	            if (product != null) {
//	                orderItemDto.setProductId(product.getId());
	                orderItemDto.setProductName(product.getTitle());
	                orderItemDto.setDiscription(product.getDescription());
	                orderItemDto.setPrice(product.getPrice());
	            }

	            orderItemDtos.add(orderItemDto);
	        }

	        orderDto.setOrderItems(orderItemDtos);
	        orderedItemsList.add(orderDto);
	    }

	    return orderedItemsList;
	}
}