package com.microservices.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
	private long userId;
	private String username;
	private String role;
}
