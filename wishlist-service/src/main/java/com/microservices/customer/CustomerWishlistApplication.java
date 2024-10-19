package com.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerWishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerWishlistApplication.class, args);
	}

}
