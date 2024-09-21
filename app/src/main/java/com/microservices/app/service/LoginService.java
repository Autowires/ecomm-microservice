package com.microservices.app.service;

import java.util.Map;

public interface LoginService {

	Map authenticateUser(String email, String password);

}
