package com.microservices.authentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.microservices.authentication.dto.UserResponseProjection;
import com.microservices.authentication.entity.MyUser.UserStatus;
import com.microservices.authentication.entity.Retailer;
import com.microservices.authentication.service.MyUserService;

@RestController
@RequestMapping("/admin/users")
public class UsersController {

	@Autowired
	private MyUserService userService;

	@GetMapping
	public ResponseEntity<List<UserResponseProjection>> getUserAndView(
			@SessionAttribute(required = false) @RequestParam(required = false) String role) {

		if (role == null || role.isBlank()) {
			return ResponseEntity.ok(userService.getAllUsers());
		} else {
			return ResponseEntity
					.ok(userService.getAllUsers().stream().filter(u -> u.getUserType().equals(role)).toList());
		}
	}

	// This method handles PUT requests to update a user's status.
	@PutMapping("/admin/users/status")
	public String putUserStatus(@RequestParam long userId, @RequestParam UserStatus status) {
		try {
			// Call the service layer to update the user status
			userService.updateUserStatus(userId, status);
			return "User status updated successfully.";
		} catch (Exception e) {
			// Handle any errors that occur during the update process
			return "Failed to update user status: " + e.getMessage();
		}

	}

	@GetMapping("/underReview")
	public ResponseEntity<?> retailersUnderReview() {
		List<Retailer> retailers = userService.retailersUnderReview();
		if (retailers == null || retailers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No retailers under review");
		}
		return ResponseEntity.ok(retailers);
	}

}
