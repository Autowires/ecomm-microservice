package com.microservices.authentication.Testcontroller;

import com.microservices.authentication.controller.UsersController;
import com.microservices.authentication.service.MyUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MyUserService userService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUserStatus() throws Exception {
        mockMvc.perform(put("/admin/users/status?userId=1&status=ACTIVE"))
                .andExpect(status().isOk());
    }

    @Test
    void testRetailersUnderReview() throws Exception {
        mockMvc.perform(get("/admin/users/underReview"))
                .andExpect(status().isOk());
    }
}