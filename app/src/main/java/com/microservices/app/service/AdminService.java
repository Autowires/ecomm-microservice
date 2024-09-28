package com.microservices.app.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.microservices.app.dto.UserStatus;

@Service
public class AdminService {

	@Autowired
	private DiscoveryClient discoveryClient;
	private final RestTemplate template = new RestTemplate();

	private URI getUri() {
		ServiceInstance serviceInstance = discoveryClient.getInstances("api-gateway").get(0);
		return serviceInstance.getUri();
	}

	public List<Map<String, Object>> getUsers() {
		String url = getUri() + "/admin/users";
		var response = template.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), List.class)
				.getBody();
		return response;
	}

	public List<Object> retailerUnderReview() {
		String baseUrl = getUri() + "/admin/users/reviewRequest";
		return template.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), List.class).getBody();
	}

	public void changeStatus(long userId, UserStatus status) {
		String baseUrl = getUri() + "/admin/users/status";
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("userId", userId)
				.queryParam("status", status);
		// Set up headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		// Create HTTP entity with the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);
		template.exchange(uriBuilder.toUriString(), HttpMethod.PUT, entity, String.class);
	}
}
