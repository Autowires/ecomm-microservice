package com.microservices.admin.service;

import java.util.List;

import com.microservices.admin.entity.MyUser;
import com.microservices.admin.entity.UserStatus;

public interface UserService {

	List<MyUser> getAllUsers();

	void updateUserStatus(long userId, UserStatus status);

}
