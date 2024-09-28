package com.microservices.authentication.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
	private String name;
	private String email;
	private Long contactNo;
	private Integer age;
	private String password;
	private String city;
	private String role;
}