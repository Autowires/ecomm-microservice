package com.microservices.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String userType;
	private String name;
	private String email;
	private Long contactNo;
	private Integer age;
	private String password;
	private String city;
	private Long aadharNumber;
	private String panNumber;
	private String gstNumber;
	private String status;
	private long adminPassword;

}
