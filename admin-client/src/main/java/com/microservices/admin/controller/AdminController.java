package com.microservices.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.admin.dto.UserDTO;
import com.microservices.admin.dto.UserStatus;

@Controller // Indicates that this class is a Spring MVC controller
public class AdminController {

	@Autowired
	private DiscoveryClient discoveryClient; // Used to find service instances

	private RestTemplate restTemplate = new RestTemplate(); // For making HTTP requests

	// Get the URI for the authentication service
	private String getAuthenticationServiceUri() {
		List<ServiceInstance> instances = discoveryClient.getInstances("api-gateway");
		ServiceInstance serviceInstance = instances.get(0); // Get the first instance
		return serviceInstance.getUri().toString(); // Return its URI
	}

	@GetMapping // Map to the index page
	public String indexPage() {
		return "index"; // Return the index view
	}

	@GetMapping("/access-denied") // Handle access denial
	public String accessDenied() {
		return "error/403"; // Return the 403 error view
	}

	@GetMapping("/admin") // Get user list for admin view
	public ModelAndView getUserAndView(@RequestParam(required = false) String role, OAuth2AuthenticationToken token) {
		ModelAndView mv = new ModelAndView("admin/users"); // Create model and view for users page
		// Construct the URL to fetch users, possibly filtering by role
		String path = role != null ? "/admin/users?role=" + role : "/admin/users";
		String baseUrl = getAuthenticationServiceUri() + path;
		// Fetch users from the authentication service
		List<UserDTO> users = restTemplate.getForObject(baseUrl, List.class);
		mv.addObject("users", users); // Add users to the model
		mv.addObject("principal", token.getPrincipal().getAttributes()); // Add user info to the model
		return mv; // Return the model and view
	}

	@GetMapping("/admin/reviewRequest") // Get retailers under review
	public ModelAndView reviewRequests(OAuth2AuthenticationToken token) {
		ModelAndView mv = new ModelAndView("admin/reviewRequest"); // Create model and view for review requests
		String baseUrl = getAuthenticationServiceUri() + "/admin/users/underReview"; // Construct URL
		// Fetch retailers under review
		List<Object> retailersUnderReview = restTemplate.getForObject(baseUrl, List.class);
		mv.addObject("users", retailersUnderReview); // Add retailers to the model
		mv.addObject("principal", token.getPrincipal().getAttributes()); // Add user info to the model
		return mv; // Return the model and view
	}

	@PutMapping("/admin/status") // Update user status
	public String updateUserStatus(@RequestParam long userId, @RequestParam UserStatus status) {
		String baseUrl = getAuthenticationServiceUri() + "/admin/users/status?userId={userId}&status={status}"; // Construct URL
		// Use RestTemplate to send the PUT request to update the status
		restTemplate.put(baseUrl, null, userId, status);

		// Redirect to the admin page after the update
		return "redirect:/admin"; 
	}

}
