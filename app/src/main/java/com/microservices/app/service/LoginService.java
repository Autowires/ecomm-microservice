package com.microservices.app.service;

import org.springframework.http.ResponseEntity;

import com.microservices.app.dto.OTPVerifyRequest;
import com.microservices.app.dto.RegisterRequest;
import com.microservices.app.dto.RetailerRegister;
import com.microservices.app.dto.User;

public interface LoginService {

	User authenticateUser(String email, String password);

	String registerRetailer(RetailerRegister request);

	ResponseEntity<String> verifyEmail(String email);

	ResponseEntity<String> verifyEmail(OTPVerifyRequest otpVerifyRequest);

	String register(RegisterRequest request);

	String existsByEmail(String email);

	String verifyOtp(String email, String otp);

	String updatePassword(String email, String password);

}
