package com.microservices.authentication.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.microservices.authentication.Repo.UserRepo;
import com.microservices.authentication.dto.LoginRequest;
import com.microservices.authentication.dto.LoginResponse;
import com.microservices.authentication.dto.RegisterRequest;
import com.microservices.authentication.entity.MyUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private UserRepo userRepository;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;



//	public ResponseEntity<String> login(LoginRequest request) {
//		try {
//			var user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
//			return new LoginResponse(jwtService.generateToken(user.getUsername()), user.getUserType());
//		} catch (AuthenticationException e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong credentials");
//		}
//	}

	public String register(RegisterRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already in use!");
		}

		MyUser user = new MyUser();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setEmail(request.getEmail());
		user.setUserType(request.getRole());
		userRepository.save(user);

		return "User registered successfully";
	}

}
