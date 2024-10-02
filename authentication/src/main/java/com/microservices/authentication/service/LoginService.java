package com.microservices.authentication.service;

import com.microservices.authentication.dto.LoginResponse;
import com.microservices.authentication.exception.UserNotFoundException;

public interface LoginService {

	String updatePassword(String email, String password);

	String existsByEmail(String email);

	LoginResponse login(String email, String password);

	String verifyEmail(String email, String otp);

	String deleteAccount(Long id) ;

}
