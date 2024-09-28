package com.microservices.authentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.authentication.Repo.UserRepo;
import com.microservices.authentication.dto.UserResponseProjection;
import com.microservices.authentication.dto.retailerDto;
import com.microservices.authentication.entity.MyUser;
import com.microservices.authentication.entity.MyUser.UserStatus;
import com.microservices.authentication.entity.Retailer;

@Service
public class MyUserService {
	@Autowired
	private UserRepo dao;

	public List<UserResponseProjection> getAllUsers() {
		// TODO Auto-generated method stub
		return dao.getAllUsers();
	}

	public void updateUserStatus(long userId, UserStatus status) {
		// TODO Auto-generated method stub
		dao.updateUserStatus(userId, status);

	}

	public List<retailerDto> retailersUnderReview() {
		
		List<retailerDto> retailers= dao.retailersUnderReview();
		System.out.println(retailers);
		return retailers;
	}

}
