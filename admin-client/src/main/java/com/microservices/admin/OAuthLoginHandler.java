package com.microservices.admin;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthLoginHandler implements AuthenticationSuccessHandler {

	private Logger logger = LogManager.getLogger();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
		OAuth2User user = oauthToken.getPrincipal();

		// Extract the user's email
		String email = (String) user.getAttributes().get("email");
		logger.debug("Authenticated email is " + email);
		if (!email.equals("srimanikanta1001@gmail.com")) {
			logger.debug("Resetting authentication");
			authentication.setAuthenticated(false);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			response.sendRedirect("/access-denied");
			return;
		}
		logger.debug("Authenticated successful");
		response.sendRedirect("/admin");
	}

}
