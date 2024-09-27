package com.microservices.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservices.app.dto.RetailerRegister;
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

	@GetMapping("/register-retailer")
	public String showRetailerRegistrationForm() {
		return "register-retailer"; // return the registration JSP page
	}

	@PostMapping("/register-retailer")
	public String registerRetailer(@ModelAttribute RetailerRegister request, Model model) {
		try {
			String message = service.registerRetailer(request);
			model.addAttribute("successMessage", message);
			return "redirect:/login"; // redirect to login page on success
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register-retailer"; // return to registration page on error
		}
	}

	@PostMapping("/sendOtp")
	@ResponseBody
	public ResponseEntity<String> sendOtp(@RequestParam String email, @RequestParam String password) {
		return service.sendOtp(email, password);
	}

	@PostMapping("/verifyOtp")
	@ResponseBody
	public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
		System.out.println(email);
		System.out.println(otp);
		return service.verifyOtp(email, otp);
	}
}