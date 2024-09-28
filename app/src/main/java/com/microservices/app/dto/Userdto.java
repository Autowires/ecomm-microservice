package com.microservices.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Userdto {
	private long id;
	private String username;
	private UserStatus status;
	private String email;
	private String userType;
	private Integer age;
	 private String contactNo; 
		/*
		 * private LocalDateTime createdAt; private LocalDateTime lastSeen;
		 */
}
