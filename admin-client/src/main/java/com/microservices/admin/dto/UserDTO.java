package com.microservices.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	private long id;
	private String username;
	private UserStatus status;
	private String email;
	private String userType;
	private Integer age;
	private String contactNo;
}
