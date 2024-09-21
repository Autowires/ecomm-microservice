package com.microservices.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	private final JwtAuthenticationFilter filter;

	public GatewayConfig(JwtAuthenticationFilter filter) {
		this.filter = filter;
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("admin-service", t -> t.path("/admin/*").uri("lb://admin"))
				.route(r -> r.path("/auth/*").uri("lb://authentication")).build();
	}
}