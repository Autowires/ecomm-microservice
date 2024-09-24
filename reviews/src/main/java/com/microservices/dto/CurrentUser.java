package com.microservices.dto;

import com.microservices.entity.UserStatus;

import lombok.Data;

@Data
public class CurrentUser {
	private long userId;
	private String role;
	private UserStatus status;
	private String username;
}