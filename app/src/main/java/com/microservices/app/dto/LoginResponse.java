package com.microservices.app.dto;

import lombok.Data;

@Data
public class LoginResponse {
	private long userId;
	private String username;
	private String role;
}
