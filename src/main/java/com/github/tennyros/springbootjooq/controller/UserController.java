package com.github.tennyros.springbootjooq.controller;

import com.example.generated.tables.records.UserRecord;
import com.github.tennyros.springbootjooq.dto.UserDto;
import com.github.tennyros.springbootjooq.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestParam String username, @RequestParam String email) {
        UserRecord createdUser = service.addUser(username, email);
        log.info("Created user: {}, with email: {}", username, email);
        UserDto userDto = new UserDto(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserRecord> userRecords = service.listUsers();
        if (userRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        log.info("List of users: {}", userRecords);
        List<UserDto> userDtos = userRecords.stream()
                .map(rec -> new UserDto(rec.getId(), rec.getUsername(), rec.getEmail()))
                .toList();
        return ResponseEntity.ok(userDtos);
    }
}
