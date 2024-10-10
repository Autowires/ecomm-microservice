package com.microservices.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.entity.Customer;
import com.microservices.authentication.service.MyUserService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private MyUserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable long userId) {
		return ResponseEntity.of(userService.getCustomer(userId));
	}
}
