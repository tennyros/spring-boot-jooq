package com.github.tennyros.springbootjooq;

import com.github.tennyros.springbootjooq.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(properties = "spring.profiles.active=test")
class SpringBootJooqApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }

}
