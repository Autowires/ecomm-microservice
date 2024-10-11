package com.microservices.authentication.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.authentication.dto.UserResponseProjection;
import com.microservices.authentication.dto.retailerDto;
import com.microservices.authentication.entity.MyUser.UserStatus;
import com.microservices.authentication.service.MyUserService;

@RestController
@RequestMapping("/admin/users") // Base URL for user-related admin operations
public class UsersController {
	

	@Autowired
	private MyUserService userService; // Service to handle user operations

	Logger log = Logger.getLogger(UsersController.class.getName()); // Logger for this class

	@GetMapping // Get all users or filter by role
	public ResponseEntity<List<UserResponseProjection>> getUserAndView(@RequestParam(required = false) String role) {
		log.info("In getUserAndView");

		// If no role is specified, return all users
		if (role == null || role.isBlank()) {
			return ResponseEntity.ok(userService.getAllUsers());
		} else {
			// Filter users by the specified role and return
			return ResponseEntity
					.ok(userService.getAllUsers().stream().filter(u -> u.getUserType().equals(role)).toList());
		}
	}

	// Update a user's status based on user ID
	@PutMapping("/status")
	public String putUserStatus(@RequestParam long userId, @RequestParam UserStatus status) {
		log.info("In putUserStatus");
		try {
			// Update the user's status via the service
			userService.updateUserStatus(userId, status);
			return "User status updated successfully.";
		} catch (Exception e) {
			// Return an error message if the update fails
			return "Failed to update user status: " + e.getMessage();
		}
	}

	// Get a list of retailers that are under review
	@GetMapping("/underReview")
	public ResponseEntity<List<retailerDto>> retailersUnderReview() {
		log.info("In retailersUnderReview");

		// Fetch and return retailers under review
		List<retailerDto> retailers = userService.retailersUnderReview();
		return ResponseEntity.ok(retailers);
	}

}
