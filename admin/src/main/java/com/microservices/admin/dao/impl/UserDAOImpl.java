package com.microservices.admin.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservices.admin.dao.UserDAO;
import com.microservices.admin.entity.Customer;
import com.microservices.admin.entity.MyUser;
import com.microservices.admin.entity.Retailer;
import com.microservices.admin.entity.UserStatus;
@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Optional<MyUser> getUser(String username, String password) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<MyUser> getAllUsers() {
		return sessionFactory.fromSession(session -> {
			return session.createQuery("from User", MyUser.class).getResultList();
		});
	}

	@Override
	public void updateUserStatus(long userId, UserStatus status) {
		sessionFactory.inTransaction(session -> {
			MyUser user = session.get(MyUser.class, userId);
			user.setStatus(status);
			session.merge(user);
		});
		
	}

}
