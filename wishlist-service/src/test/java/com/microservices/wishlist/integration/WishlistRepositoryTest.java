package com.microservices.wishlist.integration;

import com.microservices.wishlist.dto.WishlistResponse;
import com.microservices.wishlist.entity.Customer;
import com.microservices.wishlist.entity.Product;
import com.microservices.wishlist.entity.WishList;
import com.microservices.wishlist.repo.WishlistRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class WishlistRepositoryTest {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        // Create mock entities to be used in tests
        customer = new Customer();
        customer.setId(1L);
        customer.setUsername("John Doe");

        product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        product.setPrice(99.99);

        WishList wishList = new WishList();
        wishList.setId(1L);
        wishList.setCustomer(customer);
        wishList.setProduct(product);

        entityManager.persist(product);
        entityManager.persist(customer);
        wishlistRepository.save(wishList);
    }

    @Test
    @Transactional
    void testUniqueProductAndCustomer() {
        WishList wishList = new WishList();
        wishList.setId(2L);
        wishList.setCustomer(customer);
        wishList.setProduct(product);
        assertThrows(DataIntegrityViolationException.class, () -> {
            wishlistRepository.save(wishList);
        });
    }

    @Test
    void testFindWishlistsByCustomerId() {
        // Test the custom query findWishlistsByCustomerId
        List<WishlistResponse> wishlists = wishlistRepository.findWishlistsByCustomerId(1L);
        assertThat(wishlists).isNotEmpty();
        assertThat(wishlists.get(0).getProductId()).isEqualTo(1L);
        assertThat(wishlists.get(0).getProductName()).isEqualTo("Test Product");
        assertThat(wishlists.get(0).getPrice()).isEqualTo(99.99);
    }

    @Test
    void testDeleteByProductIdAndCustomerId() {
        // Test deletion by product and customer ID
        wishlistRepository.deleteByProductIdAndCustomerId(1L, 1L);
        Optional<WishList> deletedWishList = wishlistRepository.findByCustomerIdAndProductId(1L, 1L);
        assertThat(deletedWishList).isEmpty();
    }

    @Test
    void testFindByCustomerIdAndProductId() {
        // Test finding a wishlist item by customer and product ID
        Optional<WishList> foundWishList = wishlistRepository.findByCustomerIdAndProductId(1L, 1L);
        assertThat(foundWishList).isPresent();
        assertThat(foundWishList.get().getCustomer().getId()).isEqualTo(1L);
        assertThat(foundWishList.get().getProduct().getId()).isEqualTo(1L);
    }
}
