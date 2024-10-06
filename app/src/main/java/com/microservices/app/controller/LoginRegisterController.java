package com.microservices.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

	@Autowired
	private LoginService service;
	@Autowired
	 private ProductService productService;

		/*
		 * @GetMapping("/") 
		 * public String home( Model model) { 
		 * return "index"; }
		 */
	@GetMapping("/")
	public String recentAdds(Model model){
		List<Object> products=productService.recentProducts();
		model.addAttribute("newProducts", products);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
    
	@PostMapping("/Login")
	public String login(@RequestParam String email, @RequestParam String password,   
			            @RequestParam(required = false) String category,HttpSession session, Model model) {
		try {
			User user = service.authenticateUser(email, password);
			if (user == null) {
				model.addAttribute("errorMessage", "Invalid email or password.");
				return "login";
			}
			session.setAttribute("role", user.getRole());
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("username", user.getUsername());
			
			 // Redirect to the category page if category exists
	        if (category != null && !category.isEmpty()) {
	            return "redirect:/products-view?category=" + category;
	        }
			switch (user.getRole()) {
			case "RETAILER":
				return "redirect:/retailer";
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
	 @GetMapping("/products-view")
	    public String productsViews(@RequestParam(required = false) String category,
	                                @RequestParam(required = false) String search, 
	                                Model model) {
	        List<Product> products = productService.getProducts(category, search);
	        List<String> categories = productService.getAllCategories();
	        model.addAttribute("categories", categories);
	        model.addAttribute("products", products);
	        return "products";
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
			return "redirect:/login"; // redirect to login page on success
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
			return "register-retailer"; // return to registration page on error
		}
	}

	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute RegisterRequest request, Model model) {
		System.out.println(request.getAge());
		System.out.println(request.getCity());
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

	
	  @RequestMapping("/passwordRecovery") public String passwordRecoveryPage() {
	  return "passwordRecovery"; }
	  
	  @PostMapping("/forgotpassword") public String passwordRecovery(@RequestParam
	  String email,Model model) { String message = service.existsByEmail(email);
	  return "passwordRecovery"; }
	 
	  @PostMapping("/forgotpassword/verify-otp") public String
	  verifyEmail(@RequestBody OTPVerifyRequest otpVerifyRequest) { String message
	  = service.verifyOtp(otpVerifyRequest.getEmail(), otpVerifyRequest.getOtp());
	  return "passwordRecovery"; }
	 
}