package com.microservices.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservices.app.dto.OTPVerifyRequest;
import com.microservices.app.dto.Product;
import com.microservices.app.dto.RegisterRequest;
import com.microservices.app.dto.RetailerRegister;
import com.microservices.app.dto.User;
import com.microservices.app.service.LoginService;
import com.microservices.app.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

	@Autowired
	private LoginService service;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String home(Model model) {
		List<Object> product=productService.recentProducts();
		model.addAttribute("newProducts",product);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping(value = "/register/verify-email")
	@ResponseBody
	public ResponseEntity<String> verifyEmail(@RequestParam String email) {
		System.out.println("in request for " + email);
		return service.verifyEmail(email);
	}

	@PutMapping("/register/verify-email")
	@ResponseBody
	public ResponseEntity<String> verifyEmailOTP(@RequestBody OTPVerifyRequest otpVerifyRequest) {
		
		return service.verifyEmail(otpVerifyRequest);
	}

	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute RegisterRequest request, Model model) {
		try {
			String message = service.register(request);
			model.addAttribute("successMessage", message);
			return "redirect:/login"; // redirect to login page on success
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "register"; // return to registration page on error
		}
	}

	@GetMapping("/retailerDash")
	public String retailerDash(Model model) {
		return "retailer/retailerDash";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		try {
			User user = service.authenticateUser(email, password);
			if (user == null) {
				model.addAttribute("errorMessage", "Invalid email or password.");
				return "login";
			}
			session.setAttribute("role", user.getRole());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("username", user.getUsername());
			switch (user.getRole()) {
			case "RETAILER":
				return "redirect:/retailerDash";
			case "CUSTOMER":
				return "redirect:/products";
			case "ADMIN":
				return "redirect:/admin";
			}

		} catch (Exception e) {
			model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
			return "login";
		}

		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/register-retailer")
	public String showRetailerRegistrationForm() {
		return "register-retailer"; // return the registration JSP page
	}

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "register"; // return the registration JSP page
	}

	@PostMapping("/register-retailer")
	public String registerRetailer(@ModelAttribute RetailerRegister request, Model model) {
		try {
			String message = service.registerRetailer(request);
			model.addAttribute("successMessage", message);
			// redirect to login page on success
			return "redirect:/login"; 
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			// return to registration page on error
			return "register-retailer"; 
			
		}
	}

	@RequestMapping("/passwordRecovery")
	public String passwordRecoveryPage() {
		return "passwordRecovery";
	}

	@PostMapping("/forgotpassword")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> passwordRecovery(@RequestParam String email) {
	    Map<String, Object> response = new HashMap<>();

	    // Call service method to check email and send OTP
	    String message = service.existsByEmail(email);
	    System.out.println(message);
	    if (message.equals("OTP sent to your email.")) { 
	        response.put("success", true);
	        response.put("errorMessage", null); // No error message
	    } else {
	        response.put("success", false);
	        response.put("errorMessage", message); // Email not found
	    }

	    return ResponseEntity.ok(response);
	}


	

	@PostMapping("/forgotpassword/verify-otp")
	public ResponseEntity<Map<String, Object>> verifyEmail(@RequestBody OTPVerifyRequest otpVerifyRequest) {
		String message = service.verifyOtp(otpVerifyRequest.getEmail(), otpVerifyRequest.getOtp());
		Map<String, Object> response = new HashMap<>();

		if (message.equals("OTP verified.")) {
			response.put("success", true);
		} else {
			response.put("success", false);
			response.put("otpErrorMessage", message);
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/updatePassword")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody Map<String, String> request) {
	    String email = request.get("email");
	    String password = request.get("Password");
	    String message = service.updatePassword(email, password);
	    boolean success = message.contains("success");
	    Map<String, Object> response = new HashMap<>();

	    if (success) {
	        response.put("success", true);
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("errorMessage", "Password update failed.");
	        return ResponseEntity.badRequest().body(response);
	    }
	}

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "404";
			} else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
				return "403";
			}
		}
		return "error";
	}

} 