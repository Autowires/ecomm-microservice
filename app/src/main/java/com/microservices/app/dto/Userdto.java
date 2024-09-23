package com.microservices.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Userdto {
	private long id;
	private String username;
	private String password;
	private UserStatus status;
	private String email;
	private String role;

}
