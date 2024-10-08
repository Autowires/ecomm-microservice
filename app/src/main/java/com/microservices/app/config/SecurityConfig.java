package com.microservices.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservices.app.filter.ProtectedRequestFilter;

@Configuration
public class SecurityConfig {
	@Bean
	FilterRegistrationBean<ProtectedRequestFilter> requestProtectionFilter() {
		FilterRegistrationBean<ProtectedRequestFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ProtectedRequestFilter());
		// retailer request mappings
		registrationBean.addUrlPatterns("/retailer/*", "/retailerDash");
		// customer request mappings
		registrationBean.addUrlPatterns("/cart/*", "/wishlist/*", "/orders/*", "/reviews/*", "/checkout");
		registrationBean.setOrder(2);

		return registrationBean;
	}
}