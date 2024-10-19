package com.microservices.customer.wishlist.test;


import com.microservices.customer.controller.WishlistController;
import com.microservices.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WishlistController.class)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc; // class to perform http requests and http responses.

    @MockBean
    private CustomerService service;

    @Test  // when we enter 1 it will return an empty list because the user has not added the product into wishlist.
    public void testWishlistView() throws Exception {
        Mockito.when(service.getWishlistedItems(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/customers/1/wishlist"))  // if it will work it return 200 as result. succuess.
               .andExpect(status().isOk());

        verify(service).getWishlistedItems(1L);
    }

    @Test
    public void testAddProduct() throws Exception {
        doNothing().when(service).addWishlistItem(1L, 3L);         // returning success if the user1 adding the product 3 into the wishlist.

        mockMvc.perform(post("/customers/1/wishlist")
               .param("productId", "3"))
               .andExpect(status().isCreated());

        verify(service).addWishlistItem(1L, 3L);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(service).removeWishlistItem(1L, 3L);

        mockMvc.perform(delete("/customers/1/wishlist")
               .param("productId", "3"))
               .andExpect(status().isOk());

        verify(service).removeWishlistItem(1L, 3L);
    }
}