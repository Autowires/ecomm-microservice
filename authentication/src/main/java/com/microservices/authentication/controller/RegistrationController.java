package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.dto.OTPVerifyRequest;
import com.microservices.authentication.dto.RegistrationRequest;
import com.microservices.authentication.dto.RetailerRegister;
import com.microservices.authentication.service.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/register/verify-email")
	public ResponseEntity<String> verifyEmail(@RequestParam String email) {
		String message = registrationService.verifyEmail(email);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/register/verify-email")
	public ResponseEntity<String> verifyEmail(@RequestBody OTPVerifyRequest otpVerifyRequest) {
		String message = registrationService.verifyEmail(otpVerifyRequest.getEmail(), otpVerifyRequest.getOtp());
		return ResponseEntity.ok(message);
	}

	@PostMapping("/register")
	public String registerCustomer(@RequestBody @Valid RegistrationRequest request) {
		
		return registrationService.register(request);
	}

	@PostMapping("/register-retailer")
	public ResponseEntity<String> registerUser(@RequestBody @Valid RetailerRegister request) {
		String msg = registrationService.registerRetailer(request);
		return ResponseEntity.ok(msg);
	}
}
