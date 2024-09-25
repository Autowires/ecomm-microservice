package com.microservices.product.service.dto;

import java.time.LocalDate;
import java.util.List;

import com.microservices.product.service.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderDTO {
	
	private Long orderId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private double totalAmount;
    private Long buyerId;
	private String address;
	private String paymentType;
	private String Buyername;
    private String orderStatus; // Add orderStatus field
    private List<OrderItemDTO> orderItems;

}
