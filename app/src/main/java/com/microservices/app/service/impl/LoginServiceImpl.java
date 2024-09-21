package com.microservices.app.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final DiscoveryClient discoveryClient;
	private final Logger logger;
	private final RestTemplate template;

	public LoginServiceImpl(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
		logger = LogManager.getLogger();
		template = new RestTemplate();
	}

	@Override
	public Map authenticateUser(String email, String password) {
		ServiceInstance serviceInstance = discoveryClient.getInstances("api-gateway").get(0);
		System.out.println(serviceInstance.getUri());
		var request = Map.of("username", email, "password", password);
		return template.postForObject(serviceInstance.getUri() + "/auth/login", request, Map.class);
	}

}
