package com.microservices.authentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.authentication.entity.MyUser;
import com.microservices.authentication.entity.MyUser.UserStatus;
import com.microservices.authentication.service.MyUserService;
import com.microservices.authentication.service.UserService;



@RestController
@RequestMapping("/admin/users")
public class UsersController {

	@Autowired
	private MyUserService userService;

	@GetMapping
	public ResponseEntity<List<MyUser>> getUserAndView(
			@SessionAttribute(required = false) @RequestParam(required = false) String role) {

		if (role == null || role.isBlank()) {
			return ResponseEntity.ok(userService.getAllUsers());
		} else {
			return ResponseEntity.ok(userService.getAllUsers().stream().filter(u -> u.getUserType().equals(role)).toList());
		}
	}

	@PutMapping("/status")
	public String putUserStatus(@RequestParam long userId, @RequestParam UserStatus status) {

		userService.updateUserStatus(userId, status);

		return "User status updated successfully";
	}

}
