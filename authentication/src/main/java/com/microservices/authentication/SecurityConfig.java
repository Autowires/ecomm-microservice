package com.microservices.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.microservices.authentication.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(CsrfConfigurer::disable).authorizeHttpRequests(customeizer -> {
			customeizer.requestMatchers("/retailers/*").hasRole("RETAILER");
			customeizer.requestMatchers("/customers/*").hasRole("CUSTOMER");
			customeizer.requestMatchers("/admin").hasRole("ADMIN");
			customeizer.anyRequest().permitAll();
		}).build();
	}

	@Bean
	AuthenticationManager authenticationManager(UserService userService) {
		var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordEncoder());
		ProviderManager manager = new ProviderManager(provider);
		manager.setEraseCredentialsAfterAuthentication(false);
		return manager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
