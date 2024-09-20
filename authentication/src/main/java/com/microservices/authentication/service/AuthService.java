package com.microservices.authentication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.authentication.dao.UserRepository;
import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.RegisterRequest;
import com.microservices.authentication.entity.MyUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private UserRepository userRepository;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;

	public String login(LoginRequest request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			return "Login successful for user: " + request.getUsername();
		} catch (AuthenticationException e) {
			throw new RuntimeException("Invalid username or password", e);
		}
	}

	public String register(RegisterRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new RuntimeException("User already exists");
		}

		MyUser user = new MyUser();
		user.setUsername(request.getUsername());
		user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setRole(request.getRole());
		userRepository.save(user);

		return "User registered successfully";
	}

}
