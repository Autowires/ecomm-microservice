package com.microservices.product.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query("SELECT new com.microservices.product.service.dto.CartResponse( "
			+ "ci.cartItemId, p.id, p.title, p.price, ci.quantity) " + "FROM Cart c " + "JOIN c.items ci "
			+ "JOIN ci.product p " + "WHERE c.buyerId = :customerId")
	List<CartResponse> findCartItemsByCustomerId(@Param("customerId") long customerId);

}
