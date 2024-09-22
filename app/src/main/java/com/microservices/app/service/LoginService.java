package com.microservices.app.service;

import com.microservices.app.dto.User;

import org.springframework.http.ResponseEntity;

public interface LoginService {

	User authenticateUser(String email, String password);

	ResponseEntity<String> sendOtp(String email, String password);

	ResponseEntity<String> verifyOtp(String email, String otp);

}
