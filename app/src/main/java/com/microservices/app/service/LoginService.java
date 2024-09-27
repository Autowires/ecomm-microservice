package com.microservices.app.service;

import org.springframework.http.ResponseEntity;

import com.microservices.app.dto.RetailerRegister;
import com.microservices.app.dto.User;

public interface LoginService {

	User authenticateUser(String email, String password);

	ResponseEntity<String> sendOtp(String email, String password);

	ResponseEntity<String> verifyOtp(String email, String otp);

	String registerRetailer(RetailerRegister request);

}
