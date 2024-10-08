package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.LoginResponse;
import com.microservices.authentication.dto.OTPVerifyRequest;
import com.microservices.authentication.service.LoginService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private LoginService service;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		
		return ResponseEntity.ok(service.login(request.getEmail(), request.getPassword()));
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<String> passwordRecovery(@RequestParam String email) {
		String message = service.existsByEmail(email);
		return ResponseEntity.ok(message);
	}
	@PostMapping("/forgotpassword/verify-otp")
	public ResponseEntity<String> verifyEmail(@RequestBody OTPVerifyRequest otpVerifyRequest) {
		String message = service.verifyEmail(otpVerifyRequest.getEmail(), otpVerifyRequest.getOtp());
		return ResponseEntity.ok(message);
	}


	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String password) {
		String message = service.updatePassword(email, password);
		return ResponseEntity.ok(message);
	}
	
	@DeleteMapping("/deleteAccount")
	public ResponseEntity<String> deleteAccount(@RequestParam Long id){
		String message=service.deleteAccount(id);
		return ResponseEntity.ok(message);
	}
}