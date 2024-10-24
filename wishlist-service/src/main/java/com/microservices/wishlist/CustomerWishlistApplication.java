package com.microservices.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerWishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerWishlistApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestClient.Builder loadBalancedBuilder() {
		return RestClient.builder();
	}

}
