package com.microservices.app.controller.admin;

import java.util.Arrays;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.app.dto.UserStatus;
import com.microservices.app.dto.Userdto;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class UsersController {

	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate = new RestTemplate();

	private String getAuthenticationServiceUri() {
	    List<ServiceInstance> instances = discoveryClient.getInstances("authentication");
	    ServiceInstance serviceInstance = instances.get(0);
	    return serviceInstance.getUri().toString();
	}

	@GetMapping("/admin")
	public ModelAndView getUserAndView(@RequestParam(required = false) String role) {
	    ModelAndView mv = new ModelAndView("admin/users");
	    String baseUrl = getAuthenticationServiceUri() + "/admin/users";
	    
	    Object response = restTemplate.getForObject(baseUrl, Object.class);
	    
	    List<Userdto> users = new ObjectMapper().convertValue(response, new TypeReference<List<Userdto>>() {});
	    
	    if (role != null) {
	        users = users.stream().filter(u -> u.getUserType().equals(role)).toList();
	    }

	    mv.addObject("users", users);
	    return mv;
	}

	
	@GetMapping("/admin/reviewRequest")
	public ModelAndView reviewRequests() {
	    ModelAndView mv = new ModelAndView("admin/reviewRequest");
	    String baseUrl = getAuthenticationServiceUri() + "/admin/users/underReview";
	    
	    List<Object> retailersUnderReview = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), List.class).getBody();
	    
	    if (retailersUnderReview != null && !retailersUnderReview.isEmpty()) {
	        mv.addObject("users", retailersUnderReview);
	    }
	    
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
