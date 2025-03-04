package com.github.tennyros.springbootjooq.integration.service;

import com.github.tennyros.springbootjooq.config.BaseIntegrationConfigTest;
import com.github.tennyros.springbootjooq.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/cleanup.sql")
class UserServiceIT extends BaseIntegrationConfigTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser() {
        var user = userService.addUser("ivan", "ivan@mail.ru");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("ivan");
        assertThat(user.getEmail()).isEqualTo("ivan@mail.ru");
    }

    @Test
    void testListUsers() {
        userService.addUser("ivan", "ivan@mail.ru");
        userService.addUser("vadim", "vadim@mail.ru");

        var users = userService.listUsers();
        assertThat(users).hasSize(2);
    }
}
