package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.authentication.dto.UserDTO;
import com.microservices.authentication.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/register")
public class AuthenticationController {
	@Autowired
	 private LoginService service;
	
	@PostMapping("/registermail")
	public ResponseEntity<String> verifyAndRegister(@RequestParam String email){
		 String message=service.verifyEmail(email);
		return ResponseEntity.ok(message);
		
	}
	
	@PostMapping("/user")
	public String RegisterUser(@RequestBody UserDTO user) {
	   String msg=service.registerUser(user);
	    return msg;
	}

	
	@GetMapping("/verifylogin")
	public String validateUserAndSendOtp(@RequestParam String email,
			                              @RequestParam String password) {
		String msg=service.sendOtp(email,password);
		return msg;
	}
	
	@PostMapping("/forgotpassword")
	public String passwordRecovery(@RequestParam String  email) {
		String message=service.existsByEmail(email);
		return message;
		
	}
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestParam String email,
	                                             @RequestParam String password) {

	    String message = service.updatePassword(email, password);

	    if (message.equals("Password updated successfully.")) {
	        return ResponseEntity.ok(message);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);  
	    }
	}

	}
