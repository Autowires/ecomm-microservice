package com.microservices.app.service.impl;

import java.net.URI;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.microservices.app.dto.OTPVerifyRequest;
import com.microservices.app.dto.OtpVerificationRequest;
import com.microservices.app.dto.RegisterRequest;
import com.microservices.app.dto.RetailerRegister;
import com.microservices.app.dto.User;
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

	private URI getUri() {
		ServiceInstance serviceInstance = discoveryClient.getInstances("api-gateway").get(0);
		return serviceInstance.getUri();
	}

	@Override
	public User authenticateUser(String email, String password) {
		var request = Map.of("email", email, "password", password);
		return template.postForObject(getUri() + "/auth/login", request, User.class);
	}

	@Override
	public String registerRetailer(RetailerRegister request) {
		return template.postForObject(getUri() + "/auth/register-retailer", request, String.class);
	}

	@Override
	public String register(RegisterRequest request) {
		return template.postForObject(getUri() + "/auth/register", request, String.class);
	}

	@Override
	public ResponseEntity<String> verifyEmail(String email) {
		var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/auth/register/verify-email").queryParam("email", email)
				.encode().toUriString();
		try {
			return template.getForEntity(uri, String.class);
		} catch (HttpClientErrorException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
		}
	}

	@Override
	public ResponseEntity<String> verifyEmail(OTPVerifyRequest otpVerifyRequest) {
		var url = getUri() + "/auth/register/verify-email";
		System.out.println(otpVerifyRequest);
		try {
			return template.postForEntity(url, otpVerifyRequest, String.class);
		} catch (HttpClientErrorException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
		}
	}

	
	  @Override public String existsByEmail(String email) { var
	  uri=UriComponentsBuilder.fromHttpUrl(getUri()+"/auth/forgotpassword")
	  .queryParam("email",email).encode().toUriString(); return
	  template.postForObject(uri,null,String.class); }
	  
	  @Override public String verifyOtp(String email, String otp) { var uri =
	  getUri() + "/auth/forgotpassword/verify-otp";
	  
	  OtpVerificationRequest requestBody = new OtpVerificationRequest(email, otp);
	  
	  return template.postForObject(uri, requestBody, String.class); }
	 


}
