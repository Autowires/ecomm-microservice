package com.microservices.admin.service;


import java.util.List;
import java.util.Optional;

import com.microservices.admin.dto.RegistrationForm;
import com.microservices.admin.entity.MyUser;
import com.microservices.admin.entity.UserStatus;

public interface UserService {

	List<MyUser> getAllUsers();

	void updateUserStatus(long userId, UserStatus status);

}
