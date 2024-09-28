package com.microservices.authentication.dto;

import com.microservices.authentication.entity.MyUser.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class retailerDto {
	private Long id;
	private String username;
	private String email;
	private String shopName; 
	private String gstin;
	private String pannumber;
	private String address;  
	private UserStatus status;

}
