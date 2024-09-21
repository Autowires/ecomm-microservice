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

import com.microservices.authentication.dto.UserDTO;
import com.microservices.authentication.service.LoginService;

@RestController
@RequestMapping("/register")
public class AuthenticationController {

	@Autowired
	private LoginService service;

	@PostMapping("/registermail")
	public ResponseEntity<String> verifyAndRegister(@RequestParam String email) {
		String message = service.verifyEmail(email);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/user")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
		String msg = service.registerUser(user);
		return ResponseEntity.ok(msg);
	}

	@GetMapping("/verifylogin")
	public ResponseEntity<String> validateUserAndSendOtp(@RequestParam String email, @RequestParam String password) {
		String msg = service.sendOtp(email, password);
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtpForLogin(@RequestParam String email, @RequestParam String otp) {
		String msg = service.verifyOtpForLogin(email, otp);
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