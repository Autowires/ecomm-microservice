package com.microservices.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OAuthConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		return http
				.authorizeHttpRequests(requests -> requests
					.requestMatchers("/admin/**").authenticated()
					.anyRequest().permitAll()
				)
				.oauth2Login(oAuth -> oAuth
					.defaultSuccessUrl("/admin")
					.loginPage("/")
				)
				.build();
		// @formatter:on
	}
}
