package com.microservices.authentication.service;

import com.microservices.authentication.dto.LoginResponse;

public interface LoginService {

	String updatePassword(String email, String password);

	String existsByEmail(String email);

	LoginResponse login(String email, String password);

	String verifyEmail(String email, String otp);

}
