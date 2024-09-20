package com.microservices.authentication.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.authentication.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
	Optional<MyUser> findByUsername(String username);

	boolean existsByUsername(String name);
}
