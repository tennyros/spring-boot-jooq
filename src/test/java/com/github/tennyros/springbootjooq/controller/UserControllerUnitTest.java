package com.github.tennyros.springbootjooq.controller;

import com.github.tennyros.springbootjooq.service.UserService;
import jooq.tables.records.AppUserRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {

        String username = "ivan";
        String email = "ivan@mail.ru";
        AppUserRecord user = new AppUserRecord(1L, username, email);

        when(userService.addUser(username, email)).thenReturn(user);

        mockMvc.perform(post("/users")
                .param("username", username)
                .param("email", email))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.email").value(email));

        verify(userService, times(1)).addUser(username, email);
    }
}
