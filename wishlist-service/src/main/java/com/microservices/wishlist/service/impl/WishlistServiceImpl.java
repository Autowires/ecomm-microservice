package com.microservices.wishlist.service.impl;

import com.microservices.wishlist.dto.WishlistResponse;
import com.microservices.wishlist.entity.Customer;
import com.microservices.wishlist.entity.Product;
import com.microservices.wishlist.entity.WishList;
import com.microservices.wishlist.exception.CustomWishlistException;
import com.microservices.wishlist.exception.ResourceNotFoundException;
import com.microservices.wishlist.repo.CustomerRepository;
import com.microservices.wishlist.repo.ProductRepository;
import com.microservices.wishlist.repo.WishlistRepository;
import com.microservices.wishlist.service.WishlistService;
import com.microservices.wishlist.util.ServiceClient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository dao;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ServiceClient serviceClient;

    @Override
    @Transactional
    public List<WishlistResponse> getWishlistedItems(long userId) {
        return dao.findWishlistsByCustomerId(userId);
    }

    @Override
    @Transactional
    public WishList addWishlistItem(long customerId, long productId) throws CustomWishlistException {
        Customer customer = customerRepository
                .findById(customerId)
                .orElseGet(() -> customerRepository.save(serviceClient.getCustomer(customerId)));

        Product product = productRepository
                .findById(productId)
                .orElseGet(() -> productRepository.save(serviceClient.getProduct(productId)));

        WishList wishList = new WishList();
        wishList.setCustomer(customer);
        wishList.setProduct(product);

        try {
            log.info("Added new wishlist item: Customer ID - {}, Product ID - {}", customer.getId(), product.getId());
            return dao.save(wishList);
        } catch (DataIntegrityViolationException e) {
            throw new CustomWishlistException("Wishlist entry already exists for customer and product", e);
        } catch (Exception e) {
            throw new CustomWishlistException("Error adding product to wishlist", e);
        }
    }

    @Override
    @Transactional
    public void removeWishlistItem(long userId, long productId) {
        var wishlist = dao.findByCustomerIdAndProductId(userId, productId);
        if (wishlist.isEmpty())
            throw new ResourceNotFoundException("Could not find the wishlist for the product " + productId);
        dao.delete(wishlist.get());
    }
}
