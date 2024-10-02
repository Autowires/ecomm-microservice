package com.microservices.product.service.dto;

import lombok.Data;

@Data
public class CheckoutRequest {
	private String address;
	private String paymentType;
	private String buyerName;
}
