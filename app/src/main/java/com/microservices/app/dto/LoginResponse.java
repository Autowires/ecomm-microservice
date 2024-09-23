package com.microservices.app.dto;

import lombok.Data;

@Data
public class LoginResponse {
	private String token;
	private String role;
}
