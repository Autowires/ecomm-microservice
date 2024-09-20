package com.microservices.admin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.admin.entity.Customer;
import com.microservices.admin.entity.MyUser;
import com.microservices.admin.entity.Retailer;
import com.microservices.admin.entity.UserStatus;
import com.microservices.admin.dao.UserDAO;
import com.microservices.admin.dto.RegistrationForm;
import com.microservices.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO dao;

	@Override
	public List<MyUser> getAllUsers() {
		// TODO Auto-generated method stub
		return dao.getAllUsers();
	}

	@Override
	public void updateUserStatus(long userId, UserStatus status) {
		// TODO Auto-generated method stub
		dao.updateUserStatus(userId, status);

	}
}
