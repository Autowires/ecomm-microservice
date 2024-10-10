package com.microservices.product.service.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.microservices.product.service.dto.CheckoutRequest;
import com.microservices.product.service.entity.Orders;
import com.microservices.product.service.service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	@MockBean
	OrderService orderService;
	@Autowired
	MockMvc mockMvc;

	@Test
	public void testplaceOrder() throws Exception {
		Long buyerId = 1L;
		CheckoutRequest request = new CheckoutRequest("udyagiri", "cash", "sagar");

		Orders mockOrder = new Orders();
		mockOrder.setOrderId(101L);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setTotalAmount(500.0);
		mockOrder.setPaymentType("cash");
		mockOrder.setBuyerId(buyerId);
		mockOrder.setAddress("123 Main St");
		mockOrder.setBuyername("John Doe");
		Mockito.when(orderService.placeOrder(Mockito.any(CheckoutRequest.class), Mockito.eq(buyerId)))
				.thenReturn(mockOrder);

		// Convert CheckoutRequest object to JSON string
		String jsonRequest = """
				{
				    "address": "udyagiri",
				    "paymentMethod": "cash",
				    "buyerName": "sagar"
				}
				""";
		// Perform the request
		mockMvc.perform(MockMvcRequestBuilders.post("/orders/placeorder/{buyerId}", buyerId)
				.contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isCreated()) // Assert
																												// HTTP
																												// 201
																												// Created
																												// status
				.andExpect(jsonPath("$.message").value("ordered place sucessFuly"));
	}
//	 @Test
//	    public void testGetBuyerOrderById() throws Exception {
//	        // Arrange
//	        Long buyerId = 1L;
//	        
//	        // Create a mock Order object
//	        Orders mockOrder = new Orders();
//	        mockOrder.setOrderId(101L);
//	        mockOrder.setOrderDate(LocalDate.now());
//	        mockOrder.setTotalAmount(500.0);
//	        mockOrder.setPaymentType("cash");
//	        mockOrder.setBuyerId(buyerId);
//	        mockOrder.setAddress("123 Main St");
//	        mockOrder.setBuyername("John Doe");
//
//	        // Create a list of mock Orders
//	        List<Orders> mockOrdersList = Collections.singletonList(mockOrder);
//
//	        // Create a corresponding OrderDTO
//	        OrderDTO mockOrderDTO = new OrderDTO();
//	        mockOrderDTO.setOrderId(101L);
//	        mockOrderDTO.setOrderDate(LocalDate.now());
//	        mockOrderDTO.setTotalAmount(500.0);
//	        mockOrderDTO.setPaymentType("cash");
//	        mockOrderDTO.setBuyername("John Doe");
//	        mockOrderDTO.setAddress("123 Main St");
//
//	        List<OrderDTO> mockOrderDTOList = Collections.singletonList(mockOrderDTO);
//
//	        // Mock the service call
//	        Mockito.when(orderService.getBuyerOrderById(buyerId)).thenReturn(mockOrdersList);
//
//	        // Act
//	        mockMvc.perform(MockMvcRequestBuilders.get("/orders/buyerid/{buyerId}", buyerId)
//	                .contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(status().isOk()) // Assert HTTP 200 OK status
//	                .andExpect(jsonPath("$", hasSize(1))) // Check that the response has one order
//	                .andExpect(jsonPath("$[0].orderId").value(101L)) // Assert the orderId
//	                .andExpect(jsonPath("$[0].buyername").value("John Doe")) // Assert buyer name
//	                .andExpect(jsonPath("$[0].address").value("123 Main St")); // Assert address
//	    }
}
