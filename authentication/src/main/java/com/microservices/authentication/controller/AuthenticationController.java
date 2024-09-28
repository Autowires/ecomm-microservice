package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.LoginResponse;
import com.microservices.authentication.dto.OTPVerifyRequest;
import com.microservices.authentication.dto.RegistrationRequest;
import com.microservices.authentication.dto.UserDTO;
import com.microservices.authentication.service.LoginService;
import com.microservices.authentication.service.RegistrationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private LoginService service;

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(service.login(request.getEmail(), request.getPassword()));
	}

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
	public String registerCustomer(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}

	@PostMapping("/user")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
		String msg = service.registerUser(user);
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<String> passwordRecovery(@RequestParam String email) {
		String message = service.existsByEmail(email);
		return ResponseEntity.ok(message);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String password) {
		String message = service.updatePassword(email, password);
		return ResponseEntity.ok(message);
	}
}