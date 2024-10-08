package com.microservices.authentication.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.authentication.dto.UserResponseProjection;
import com.microservices.authentication.dto.retailerDto;
import com.microservices.authentication.entity.MyUser;
import com.microservices.authentication.entity.MyUser.UserStatus;

@Repository
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

	@Query("SELECT u.id as id, u.username as username, u.status as status, u.email as email, "
			+ "u.userType as userType, u.age as age, u.contactNo as contactNo, "
			+ "u.createdAt as createdAt, u.lastSeen as lastSeen "
			+ "FROM MyUser u where u.status in ('DEACTIVATED','ACTIVE')")
	List<UserResponseProjection> getAllUsers();

	// Update user status by userId
	@Modifying
	@Transactional
	@Query("UPDATE MyUser u SET u.status = :status WHERE u.id = :userId")
	void updateUserStatus(@Param("userId") long userId, @Param("status") UserStatus status);

	@Query("SELECT new com.microservices.authentication.dto.retailerDto(r.id, r.username, r.email, r.shopName, r.GSTIN, r.pannumber, r.address, r.status) "
			+ "FROM Retailer r WHERE r.status in ('UNDER_REVIEW','REJECTED')")
	List<retailerDto> retailersUnderReview();
}
