package com.microservices.authentication.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.authentication.entity.MyUser;
import com.microservices.authentication.entity.MyUser.UserStatus;

public interface UserRepo extends JpaRepository<MyUser, Long> {

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM MyUser u WHERE u.email = :email OR u.contactNo = :contactNo")
	boolean existsByDetails(@Param("email") String email, @Param("contactNo") Long contactNo);

	@Query("SELECT u FROM MyUser u WHERE u.email = :email AND u.password = :password")
	MyUser findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM MyUser u WHERE u.email = :email")
	boolean existsBymail(@Param("email") String email);

	@Modifying
	@Transactional
	@Query("UPDATE MyUser u SET u.password = :password WHERE u.email = :email")
	int updatePassword(@Param("password") String password, @Param("email") String email);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM MyUser u WHERE u.email = :email")
	boolean existsByDetails(String email);

	boolean existsByUsername(String username);

	Optional<MyUser> findByUsername(String username);

    Optional<MyUser> findByEmail(String email);
    
    // Get all users
    @Query("SELECT u FROM MyUser u")
    List<MyUser> getAllUsers();

    // Update user status by userId
    @Modifying
    @Transactional
    @Query("UPDATE MyUser u SET u.status = :status WHERE u.id = :userId")
    void updateUserStatus(@Param("userId") long userId, @Param("status") UserStatus status);

    
}
