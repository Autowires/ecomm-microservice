package com.microservices.authentication.dto;

import lombok.Data;

@Data
public class RetailerRegister extends RegistrationRequest {
	private String shopName;
	private String GSTIN;
	private String pannumber;
	private String address;
}
