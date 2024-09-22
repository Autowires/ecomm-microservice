package com.microservices.authentication.dto;

import lombok.Data;

@Data
public class OTPVerifyRequest {
    private String email;
    private String otp;
}
