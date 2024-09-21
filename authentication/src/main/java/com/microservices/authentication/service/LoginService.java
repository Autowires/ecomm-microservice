package com.microservices.authentication.service;

import com.microservices.authentication.dto.UserDTO;
import com.microservices.authentication.exception.UserNotFoundException;
import com.microservices.authentication.exception.UserSuspendedException;

public interface LoginService {

	 String registerUser(UserDTO user);
	 
	 String  sendOtp(String email, String password);

	 String verifyEmail(String email);

	String updatePassword(String email, String password);

	String existsByEmail(String email);


	

}
