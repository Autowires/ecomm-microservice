package com.microservices.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.microservices.orders.enums.OrderStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
	private Long orderId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private BigDecimal totalAmount;
    private Integer totalQuantity;
    private OrderStatus orderStatus; // Add orderStatus field
    private List<OrderItemDTO> orderItems;


}
