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
        logger.info("Authenticating user with email: {}", email);
        var request = Map.of("email", email, "password", password);
        return template.postForObject(getUri() + "/auth/login", request, User.class);
    }

    @Override
    public String registerRetailer(RetailerRegister request) {
        logger.info("Registering retailer: {}", request);
        return template.postForObject(getUri() + "/auth/register-retailer", request, String.class);
    }

    @Override
    public String register(RegisterRequest request) {
        logger.info("Registering user: {}", request);
        return template.postForObject(getUri() + "/auth/register", request, String.class);
    }

    @Override
    public ResponseEntity<String> verifyEmail(String email) {
        logger.info("Verifying email: {}", email);
        var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/auth/register/verify-email")
                .queryParam("email", email)
                .encode()
                .toUriString();
        try {
            return template.getForEntity(uri, String.class);
        } catch (HttpClientErrorException ex) {
            logger.error("Error verifying email: {}", email, ex);
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        }
    }

    @Override
    public ResponseEntity<String> verifyEmail(OTPVerifyRequest otpVerifyRequest) {
        logger.info("Verifying email with OTP: {}", otpVerifyRequest);
        var url = getUri() + "/auth/register/verify-email";
        try {
            return template.postForEntity(url, otpVerifyRequest, String.class);
        } catch (HttpClientErrorException ex) {
            logger.error("Error verifying OTP for email: {}", otpVerifyRequest.getEmail(), ex);
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        }
    }

    @Override
    public String existsByEmail(String email) {
        logger.info("Checking existence by email: {}", email);
        var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/auth/forgotpassword")
                .queryParam("email", email)
                .encode()
                .toUriString();
        return template.postForObject(uri, null, String.class);
    }

    @Override
    public String verifyOtp(String email, String otp) {
        logger.info("Verifying OTP for email: {} with OTP: {}", email, otp);
        var uri = getUri() + "/auth/forgotpassword/verify-otp";
        OtpVerificationRequest requestBody = new OtpVerificationRequest(email, otp);
        return template.postForObject(uri, requestBody, String.class);
    }

    @Override
    public String updatePassword(String email, String password) {
        logger.info("Updating password for email: {}", email);
        var uri = getUri() + "/auth/updatePassword?email={1}&password={2}";
        return template.postForObject(uri, null, String.class, email, password);
    }
}
