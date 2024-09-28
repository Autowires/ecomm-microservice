package com.microservices.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {
	@NotNull
	private String username;
	@Email
	private String email;
	private Long contactNo;
	private Integer age;
	@Size(min = 4)
	private String password;
	private String city;
}