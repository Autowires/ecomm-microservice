package com.microservices.product.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.product.service.dto.CartResponse;
import com.microservices.product.service.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT new com.microservices.product.service.dto.CartResponse( "
			+ "c.id, p.id, p.title, p.price, c.quantity) " + "FROM CartItem c " + "JOIN c.product p "
			+ "WHERE c.customer.id = :customerId")
	List<CartResponse> findCartItemsByCustomerId(@Param("customerId") long customerId);

}
