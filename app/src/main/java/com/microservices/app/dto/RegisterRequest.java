package com.microservices.app.dto;

import lombok.Data;

@Data
public class RegisterRequest {
	private String username;
	private String password;
	private String email;
	private Long contactNo;
	private Integer age;
	private String city;
}
