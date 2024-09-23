package com.microservices.app.dto;

import lombok.Data;

@Data
public class OTPVerifyRequest {
	private String email;
	private String otp;
}
