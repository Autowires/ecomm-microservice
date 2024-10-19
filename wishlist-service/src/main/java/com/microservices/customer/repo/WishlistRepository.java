package com.microservices.customer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.customer.dto.WishlistResponse;
import com.microservices.customer.entity.WishList;

public interface WishlistDAO extends JpaRepository<WishList, Long> {
	@Query("SELECT new com.microservices.customer.dto.WishlistResponse(w.id, w.product.id, w.product.title, w.product.price) "
			+ "FROM WishList w " + "WHERE w.customer.id = :customerId")
	List<WishlistResponse> findWishlistsByCustomerId(@Param("customerId") long customerId);

	Optional<WishList> findByCustomerIdAndProductId(long userId, long productId);

	List<WishList> findAllByCustomerIdAndProductId(long userId, long productId);

}
