package com.microservices.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface LoginService {

	Map authenticateUser(String email, String password);

	ResponseEntity<String> sendOtp(String email, String password);

	ResponseEntity<String> verifyOtp(String email, String otp);

}
