package com.microservices.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.app.dto.User;
import com.microservices.app.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

	@Autowired
	private LoginService service;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		User user = service.authenticateUser(email, password);
		if (user == null) {
			model.addAttribute("errorMessage", "Invalid email or password.");
			return "login";
		}
		session.setAttribute("user", user);
		switch (user.getRole()) {
		case "RETAILER":
			return "redirect:/retailer";
		case "CUSTOMER":
			return "redirect:/products";
		case "ADMIN":
			return "redirect:/admin";
		}
		session.invalidate();
		return "redirect:/";
	}

}