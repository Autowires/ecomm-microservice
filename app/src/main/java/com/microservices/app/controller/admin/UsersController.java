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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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
        // Discover the "admin" service instances
        List<ServiceInstance> instances = discoveryClient.getInstances("admin");

        if (instances.isEmpty()) {
            // Handle case where service is not found
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "Service not available");
            return mv;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/admin/users";

        // Append the role parameter if present
        if (role != null && !role.isEmpty()) {
            baseUrl += "?role=" + role;
        }

        // Debugging: Log the URL being called
        System.out.println("Calling URL: " + baseUrl);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // Create HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String response;
        try {
            // Call the external service
            response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class).getBody();
            // Debugging: Log the response
            System.out.println("Response from admin service: " + response);
        } catch (Exception e) {
            // Handle the error gracefully
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "Failed to contact admin service: " + e.getMessage());
            return mv;
        }

        // Deserialize the response into a list of users
        ObjectMapper objectMapper = new ObjectMapper();
        List<Userdto> users;
        try {
            users = objectMapper.readValue(response, new TypeReference<List<Userdto>>() {});
        } catch (JsonProcessingException e) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "Failed to parse user data: " + e.getMessage());
            return mv;
        }

        // Set up ModelAndView
        ModelAndView mv = new ModelAndView("admin/users"); // Ensure "users" view exists
        mv.addObject("users", users); // Pass the parsed user list to the view

        return mv;
    }
    
    @RequestMapping(value = "/admin/users/status", method = RequestMethod.PUT)
    public ModelAndView putUserStatus( 
                                      @RequestParam long userId, 
                                      @RequestParam UserStatus status) {
        
        // Discover the "admin" service instances
        List<ServiceInstance> instances = discoveryClient.getInstances("admin");

        if (instances.isEmpty()) {
            // Handle case where service is not found
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
        ModelAndView mv = new ModelAndView("redirect:/admin/users");
        return mv;
    }




    
    
  

}
