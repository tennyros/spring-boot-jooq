package com.github.tennyros.springbootjooq.unit.service;

import com.github.tennyros.springbootjooq.repository.UserRepository;
import com.github.tennyros.springbootjooq.service.UserService;
import jooq.tables.records.AppUserRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        var user = new AppUserRecord(1L, "ivan", "ivan@mail.ru");
        when(userRepository.createUser(any())).thenReturn(user);

        var createdUser = userService.addUser("ivan", "ivan@mail.ru");

        assertNotNull(createdUser);
        assertEquals("ivan", createdUser.getUsername());
        assertEquals("ivan@mail.ru", createdUser.getEmail());

        verify(userRepository, times(1)).createUser(any());
    }
}
