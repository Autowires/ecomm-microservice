package com.microservices.admin.dao;

import java.util.List;
import java.util.Optional;

import com.microservices.admin.entity.Customer;
import com.microservices.admin.entity.MyUser;
import com.microservices.admin.entity.Retailer;
import com.microservices.admin.entity.UserStatus;

public interface UserDAO {
	Optional<MyUser> getUser(String username, String password);

	List<MyUser> getAllUsers();

	void updateUserStatus(long userId, UserStatus status);

}
