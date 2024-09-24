package com.microservices.app.dto;

import lombok.Data;

@Data
public class User {
	private long id;
	private String username;
	private String role;
	private String status;
}