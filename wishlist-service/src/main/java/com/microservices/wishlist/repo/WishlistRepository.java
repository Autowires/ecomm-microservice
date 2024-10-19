package com.microservices.wishlist.repo;

import com.microservices.wishlist.dto.WishlistResponse;
import com.microservices.wishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishList, Long> {
	@Query("SELECT new com.microservices.wishlist.dto.WishlistResponse(w.id, w.product.id, w.product.title, w.product.price) "
			+ "FROM WishList w " + "WHERE w.customer.id = :customerId")
	List<WishlistResponse> findWishlistsByCustomerId(@Param("customerId") long customerId);

	void deleteByProductIdAndCustomerId(long productId, long userId);

    Optional<WishList> findByCustomerIdAndProductId(long userId, long productId);
}
