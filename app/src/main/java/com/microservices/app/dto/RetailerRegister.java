package com.microservices.app.dto;

import lombok.Data;

@Data
public class RetailerRegister extends RegisterRequest {
	private String shopName;
	private String GSTIN;
	private String pannumber;
	private String address;
}
