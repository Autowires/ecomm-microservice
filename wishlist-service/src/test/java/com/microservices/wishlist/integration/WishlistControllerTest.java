package com.microservices.wishlist.integration;

import com.microservices.wishlist.entity.Customer;
import com.microservices.wishlist.entity.Product;
import com.microservices.wishlist.exception.CustomerNotFoundException;
import com.microservices.wishlist.exception.ProductNotFoundException;
import com.microservices.wishlist.repo.CustomerRepository;
import com.microservices.wishlist.repo.ProductRepository;
import com.microservices.wishlist.util.ServiceClient;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:test"
})
@AutoConfigureMockMvc
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceClient serviceClient;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;
    private Product testProduct;

    @BeforeEach
    public void init() {
        testCustomer = new Customer(1, "John");
        testProduct = new Product(1, "Product A", 100);
        customerRepository.save(testCustomer);
        productRepository.save(testProduct);
    }

    @Test
    @Transactional
    public void testAddProduct200() throws Exception {
        // Perform the add product to wishlist operation
        when(serviceClient.getProduct(anyLong())).thenReturn(null);
        when(serviceClient.getCustomer(anyLong())).thenReturn(null);
        mockMvc.perform(post("/customers/1/wishlist").param("productId", "1"))
                .andExpect(status().isCreated());

        // Verify that the external services were called
        verify(serviceClient, never()).getProduct(anyLong());
        verify(serviceClient, never()).getCustomer(anyLong());
    }

    @Test
    @Transactional
    public void testAddProductWithServiceClient() throws Exception {
        Customer c = new Customer(2, "New");
        Product p = new Product(2, "Product", 89);
        when(serviceClient.getProduct(2)).thenReturn(p);
        when(serviceClient.getCustomer(2)).thenReturn(c);
        mockMvc.perform(post("/customers/1/wishlist").param("productId", "2"))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/customers/2/wishlist").param("productId", "2"))
                .andExpect(status().isCreated());

        // Verify that the external services were called
        verify(serviceClient, never()).getCustomer(1);
        verify(serviceClient, never()).getProduct(1);
        verify(serviceClient, times(1)).getProduct(2);
        verify(serviceClient, times(1)).getCustomer(2);
    }

    @Test
    void testAddProduct404Customer() throws Exception {
        when(serviceClient.getCustomer(anyLong())).thenThrow(new CustomerNotFoundException(1));
        mockMvc.perform(post("/customers/2/wishlist").param("productId", "1"))
                .andExpect(status().isNotFound());

        // Verify that the external services were called
        verify(serviceClient, times(1)).getCustomer(anyLong());
    }

    @Test
    void testAddProduct404Product() throws Exception {
        when(serviceClient.getProduct(anyLong())).thenThrow(new ProductNotFoundException(2));
        mockMvc.perform(post("/customers/1/wishlist").param("productId", "2"))
                .andExpect(status().isNotFound());

        // Verify that the external services were called
        verify(serviceClient, times(1)).getProduct(anyLong());
    }

    @Test
    void testAddProduct409() throws Exception {
        mockMvc.perform(post("/customers/1/wishlist").param("productId", "1"))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/customers/1/wishlist").param("productId", "1"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testWishlistView() throws Exception {
        // Perform the view wishlist operation
        mockMvc.perform(get("/customers/1/wishlist"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Perform the delete product from wishlist operation
        mockMvc.perform(delete("/customers/1/wishlist").param("productId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProductNotInWishlist() throws Exception {
        // Attempt to delete a product that doesn't exist in the wishlist
        mockMvc.perform(delete("/customers/1/wishlist").param("productId", "999"))
                .andExpect(status().isNotFound());
    }
}
