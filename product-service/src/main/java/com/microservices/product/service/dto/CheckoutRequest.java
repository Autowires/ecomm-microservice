package com.microservices.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutRequest {
	private String address;
	private String paymentType;
	private String name;
}
