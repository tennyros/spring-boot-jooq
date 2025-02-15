package com.github.tennyros.springbootjooq.controller;

import com.github.tennyros.springbootjooq.config.BaseIntegrationConfigTest;
import com.github.tennyros.springbootjooq.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scripts/cleanup.sql")
class UserControllerTest extends BaseIntegrationConfigTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateUserAndGetAll() {
        String username = "Ivan";
        String email = "ivan@example.com";
        String url = "/users?username=" + username + "&email=" + email;

        var responseEntity = restTemplate.postForEntity(url, null, UserDto.class);
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);

        var getResponse = restTemplate.getForEntity("/users", UserDto[].class);
        UserDto[] users = getResponse.getBody();
        assertThat(users)
                .isNotNull()
                .anyMatch(u -> username.equals(u.username()));
    }
}
