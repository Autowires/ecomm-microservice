package com.microservices.app.dto;

import lombok.Data;

@Data
public class CheckoutRequest {
	private String address;
	private String paymentType;
	private String name;
}