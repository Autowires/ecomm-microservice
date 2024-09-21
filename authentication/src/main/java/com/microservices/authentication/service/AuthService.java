package com.microservices.authentication.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.microservices.authentication.dao.UserRepository;
import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.LoginResponse;
import com.microservices.authentication.dto.RegisterRequest;
import com.microservices.authentication.entity.MyUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private UserRepository userRepository;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;

	public LoginResponse login(LoginRequest request) {
		try {
			var authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			return new LoginResponse(jwtService.generateToken(authentication.getName()),
					authentication.getAuthorities().iterator().next().getAuthority());
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong credentials");
		}
	}

	public String register(RegisterRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already in use!");
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
