package com.microservices.product.service.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderDTO {
	private Long orderId;
	private LocalDate orderDate;
	private double totalAmount;
//    private Long buyerId;
	private String address;
	private String paymentType;
	private String Buyername;
	private List<OrderItemDTO> orderItems;

}
