package com.microservices.authentication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RetailerRegister extends RegistrationRequest {
	@NotNull
	private String shopName;
	@NotNull
	private String GSTIN;
	@NotNull
	@Size(min = 10, max = 10)
	private String pannumber;
	@NotNull
	private String address;
}
