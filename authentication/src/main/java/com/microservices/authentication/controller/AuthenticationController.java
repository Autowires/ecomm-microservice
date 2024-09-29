package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.LoginResponse;
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

	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String password) {
		String message = service.updatePassword(email, password);
		return ResponseEntity.ok(message);
	}
}