package com.microservices.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservices.app.dto.Product;
import com.microservices.app.service.LoginService;
import com.microservices.app.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

	@Autowired
	private LoginService service;
	
    @Autowired
    private ProductService productService;

	@GetMapping("/")
	public String home(Model model) {
		List<Object> products=productService.recentProducts();
		model.addAttribute("newProducts", products);
		System.out.println(products);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		var response = service.authenticateUser(email, password);
		if (response == null) {
			model.addAttribute("errorMessage", "Invalid email or password.");
			return "login";
		}
		session.setAttribute("token", response.get("token"));
		switch (response.get("role").toString()) {
		case "ROLE_RETAILER":
			return "redirect:/retailer";
		case "ROLE_CUSTOMER":
			return "redirect:/products";
		case "ROLE_ADMIN":
			return "redirect:/admin";
		}
		session.invalidate();
		return "redirect:/";
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