package com.github.tennyros.springbootjooq.unit.controller;

import com.github.tennyros.springbootjooq.controller.UserController;
import com.github.tennyros.springbootjooq.dto.UserDto;
import com.github.tennyros.springbootjooq.service.UserService;
import jooq.tables.records.AppUserRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser() {
        String username = "ivan";
        String email = "ivan@gmail.com";
        var user = new AppUserRecord(1L, username, email);

        when(userService.addUser(username, email)).thenReturn(user);

        var response = userController.createUser(username, email);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(username, response.getBody().username());
        assertEquals(email, response.getBody().email());

        verify(userService, times(1)).addUser(username, email);
    }
}
