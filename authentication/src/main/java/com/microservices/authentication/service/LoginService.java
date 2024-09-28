package com.microservices.authentication.service;

import com.microservices.authentication.dto.LoginResponse;
import com.microservices.authentication.dto.UserDTO;

public interface LoginService {

	String registerUser(UserDTO user);

	String updatePassword(String email, String password);

	String existsByEmail(String email);

	LoginResponse login(String email, String password);

}
