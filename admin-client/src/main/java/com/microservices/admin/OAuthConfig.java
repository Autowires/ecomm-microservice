package com.microservices.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OAuthConfig {

	@Autowired
	private OAuthLoginHandler oAuthLoginHandler;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		return http
				.csrf(CsrfConfigurer::disable)
				.authorizeHttpRequests(requests -> requests
					.requestMatchers("/admin/**").authenticated()
					.anyRequest().permitAll()
				)
				.oauth2Login(oAuth -> oAuth
					.defaultSuccessUrl("/admin")
					.loginPage("/")
					.successHandler(oAuthLoginHandler)
				)
				.exceptionHandling(config -> {
					config.accessDeniedPage("/access-denied");
				})
				.build();
		// @formatter:on
	}
}
