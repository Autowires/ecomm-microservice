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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.app.dto.UserStatus;
import com.microservices.app.dto.Userdto;


@Controller
public class UsersController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/admin")
	public ModelAndView getUserAndView(@RequestParam(required = false) String role) {
	    // Discover instances of the "authentication" service
	    List<ServiceInstance> instances = discoveryClient.getInstances("authentication");

	    // Handle the case where no instances are found
	    if (instances == null || instances.isEmpty()) {
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Authentication service is not available");
	        return mv;
	    }

	    // Get the first available instance
	    ServiceInstance serviceInstance = instances.get(0);
	    String baseUrl = serviceInstance.getUri().toString() + "/admin/users";

	    // Append the le' parameter if provided

	    // Debugging: Log the URL being called
	    System.out.println("Calling URL: " + baseUrl);

	    // Create RestTemplate to call the external service
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    String response;
	    try {
	        // Make the GET request to the admin service
	        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class);

	        // Check if the response is null or contains no data
	        if (responseEntity == null || responseEntity.getBody() == null) {
	            throw new Exception("Received null response from admin service");
	        }

	        response = responseEntity.getBody();

	        // Debugging: Log the response
	        System.out.println("Response from admin service: " + response);

	    } catch (HttpClientErrorException e) {
	        // Handle HTTP client errors (4xx)
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Client error: " + e.getStatusCode());
	        return mv;
	    } catch (ResourceAccessException e) {
	        // Handle connectivity issues (service not reachable)
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Service not reachable: " + e.getMessage());
	        return mv;
	    } catch (Exception e) {
	        // Handle all other errors
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Failed to contact admin service: " + e.getMessage());
	        return mv;
	    }

	    // Deserialize the JSON response into a list of Userdto objects
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<Userdto> users;
	    try {
	        users = objectMapper.readValue(response, new TypeReference<List<Userdto>>() {});
	    } catch (JsonProcessingException e) {
	        // Handle JSON parsing errors
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Failed to parse user data: " + e.getMessage());
	        return mv;
	    }

	    // Ensure the list of users is not null
	    if (users == null || users.isEmpty()) {
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "No users found");
	        return mv;
	    }

	    // Set up ModelAndView and pass the user list to the view
	    ModelAndView mv = new ModelAndView("admin/users"); // Ensure this view exists
	    System.out.println(users);
	    if(role != null) {
	    	users = users.stream().filter(u -> u.getUserType().equals(role)).toList();
	    }
	    mv.addObject("users", users);

	    return mv;
	}

	

	@PutMapping("/admin/users/status")
	public ModelAndView putUserStatus(@RequestParam long userId, @RequestParam UserStatus status) {
	    // Discover the "admin" service instances
	    List<ServiceInstance> instances = discoveryClient.getInstances("admin");

	    if (instances.isEmpty()) {
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Service not available");
	        return mv;
	    }

	    ServiceInstance serviceInstance = instances.get(0);
	    String baseUrl = serviceInstance.getUri().toString() + "/admin/users/status";

	    // Create the URL for PUT request with userId and status as parameters
	    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
	            .queryParam("userId", userId)
	            .queryParam("status", status);

	    // Debugging: Log the URL being called
	    System.out.println("Calling URL: " + uriBuilder.toUriString());

	    // Create a RestTemplate instance
	    RestTemplate restTemplate = new RestTemplate();

	    // Set up headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	    // Create HTTP entity with the headers
	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    try {
	        // Send PUT request to update the user status
	        restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.PUT, entity, String.class);
	    } catch (Exception e) {
	        // Handle the error gracefully
	        ModelAndView mv = new ModelAndView("error");
	        mv.addObject("message", "Failed to update user status: " + e.getMessage());
	        return mv;
	    }

	    // Redirect back to the users page after the update
	    return new ModelAndView("redirect:/admin/users");
	}





    
    
  

}
