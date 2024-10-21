package com.microservices.wishlist.unit;

import com.microservices.wishlist.dto.WishlistResponse;
import com.microservices.wishlist.entity.Customer;
import com.microservices.wishlist.entity.Product;
import com.microservices.wishlist.entity.WishList;
import com.microservices.wishlist.exception.CustomWishlistException;
import com.microservices.wishlist.exception.CustomerNotFoundException;
import com.microservices.wishlist.exception.ProductNotFoundException;
import com.microservices.wishlist.exception.ResourceNotFoundException;
import com.microservices.wishlist.repo.CustomerRepository;
import com.microservices.wishlist.repo.ProductRepository;
import com.microservices.wishlist.repo.WishlistRepository;
import com.microservices.wishlist.service.impl.WishlistServiceImpl;
import com.microservices.wishlist.util.ServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ServiceClient serviceClient;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    private Customer mockCustomer;
    private Product mockProduct;
    private WishList mockWishList;

    @BeforeEach
    void setUp() {
        // Initialize mock data
        mockCustomer = new Customer();
        mockCustomer.setId(1L);
        mockCustomer.setUsername("John Doe");

        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setTitle("Sample Product");
        mockProduct.setPrice(100.00);

        mockWishList = new WishList();
        mockWishList.setId(1L);
        mockWishList.setCustomer(mockCustomer);
        mockWishList.setProduct(mockProduct);
    }

    @Test
    void testGetWishlistedItems() {
        // Mock repository response
        WishlistResponse wishlistResponse = new WishlistResponse(1L, 1L, "Sample Product", 100.00);
        when(wishlistRepository.findWishlistsByCustomerId(1L)).thenReturn(List.of(wishlistResponse));

        // Call the method
        List<WishlistResponse> wishlistedItems = wishlistService.getWishlistedItems(1L);

        // Verify the results
        assertNotNull(wishlistedItems);
        assertEquals(1, wishlistedItems.size());
        assertEquals("Sample Product", wishlistedItems.get(0).getProductName());

        // Verify interaction with the repository
        verify(wishlistRepository, times(1)).findWishlistsByCustomerId(1L);
    }

    @Test
    void testAddWishlistItem() throws CustomWishlistException {
        // Mock repository and service client responses
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(mockCustomer));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));
        when(wishlistRepository.save(any(WishList.class))).thenReturn(mockWishList);

        // Call the method
        WishList addedWishList = wishlistService.addWishlistItem(1L, 1L);

        // Verify the results
        assertNotNull(addedWishList);
        assertEquals(mockCustomer.getId(), addedWishList.getCustomer().getId());
        assertEquals(mockProduct.getId(), addedWishList.getProduct().getId());

        // Verify interaction with repositories
        verify(customerRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(1L);
        verify(wishlistRepository, times(1)).save(any(WishList.class));
    }

    @Test
    void testAddProductThrowsProductNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(serviceClient.getProduct(1L)).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> {
            wishlistService.addWishlistItem(1,1);
        });

        verify(productRepository).findById(1L);
        verify(serviceClient).getProduct(1);
    }

    @Test
    void testAddProductThrowsCustomerNotFoundException() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(serviceClient.getCustomer(1L)).thenThrow(CustomerNotFoundException.class);
        assertThrows(CustomerNotFoundException.class, () -> {
            wishlistService.addWishlistItem(1,1);
        });

        verify(customerRepository).findById(1L);
        verify(serviceClient).getCustomer(1);
    }

    @Test
    void testAddWishlistItemThrowsCustomWishlistException() {
        // Mock repository and service client responses
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(mockCustomer));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));
        when(wishlistRepository.save(any(WishList.class))).thenThrow(new CustomWishlistException("Entry exists", null));

        // Verify the exception is thrown
        assertThrows(CustomWishlistException.class, () -> wishlistService.addWishlistItem(1L, 1L));
    }

    @Test
    void testRemoveWishlistItem() {
        // Mock repository response
        when(wishlistRepository.findByCustomerIdAndProductId(1L, 1L)).thenReturn(Optional.of(mockWishList));

        // Call the method
        wishlistService.removeWishlistItem(1L, 1L);

        // Verify interaction with the repository
        verify(wishlistRepository, times(1)).findByCustomerIdAndProductId(1L, 1L);
        verify(wishlistRepository, times(1)).delete(mockWishList);
    }

    @Test
    void testRemoveWishlistItemThrowsResourceNotFoundException() {
        // Mock repository response
        when(wishlistRepository.findByCustomerIdAndProductId(1L, 1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> wishlistService.removeWishlistItem(1L, 1L));
    }
}
