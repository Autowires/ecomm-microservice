package com.microservices.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route(t -> t.path("/admin/**").uri("lb://authentication"))
				.route(r -> r.path("/auth/**").uri("lb://authentication")).build();
	}
}