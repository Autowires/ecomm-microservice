package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.RegisterRequest;
import com.microservices.authentication.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthnController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
}
