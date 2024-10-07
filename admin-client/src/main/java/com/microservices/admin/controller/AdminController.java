package com.microservices.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.admin.dto.UserDTO;
import com.microservices.admin.dto.UserStatus;

@Controller
public class AdminController {

	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate = new RestTemplate();

	private String getAuthenticationServiceUri() {
		List<ServiceInstance> instances = discoveryClient.getInstances("api-gateway");
		ServiceInstance serviceInstance = instances.get(0);
		return serviceInstance.getUri().toString();
	}

	@GetMapping
	public String indexPage() {
		return "index";
	}

	@GetMapping("/admin")
	public ModelAndView getUserAndView(@RequestParam(required = false) String role) {
		ModelAndView mv = new ModelAndView("admin/users");
		String path = role != null ? "/admin/users?role=" + role : "/admin/users";
		String baseUrl = getAuthenticationServiceUri() + path;
		List<UserDTO> users = restTemplate.getForObject(baseUrl, List.class);
		mv.addObject("users", users);
		return mv;
	}

	@GetMapping("/admin/reviewRequest")
	public ModelAndView reviewRequests() {
		ModelAndView mv = new ModelAndView("admin/reviewRequest");
		String baseUrl = getAuthenticationServiceUri() + "/admin/users/underReview";
		List<Object> retailersUnderReview = restTemplate.getForObject(baseUrl, List.class);
		mv.addObject("users", retailersUnderReview);
		return mv;
	}

	@PutMapping("/admin/status")
	public String updateUserStatus(@RequestParam long userId, @RequestParam UserStatus status) {
		String baseUrl = getAuthenticationServiceUri() + "/admin/users/status?userId={userId}&status={status}";

		// Log the URL being called for debugging
		System.out.println("Calling URL: " + baseUrl);

		// Use RestTemplate to send the PUT request
		restTemplate.put(baseUrl, null, userId, status);

		// Redirect to the appropriate page after the update
		return "redirect:/admin";
	}

}
