package com.microservices.app.service;

import com.microservices.app.dto.User;

public interface LoginService {

	User authenticateUser(String email, String password);

}
