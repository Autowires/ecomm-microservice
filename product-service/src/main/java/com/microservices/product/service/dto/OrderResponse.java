package com.microservices.product.service.dto;

import com.microservices.product.service.entity.Orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	private Orders orders;
	private String email;

}
