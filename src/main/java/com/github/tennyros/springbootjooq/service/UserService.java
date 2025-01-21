package com.github.tennyros.springbootjooq.service;

import com.example.generated.tables.records.UserRecord;
import com.github.tennyros.springbootjooq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserRecord addUser(String username, String email) {
        UserRecord newUser = new UserRecord();
        newUser.setUsername(username);
        newUser.setEmail(email);

        return repository.createUser(newUser);
    }

    public List<UserRecord> listUsers() {
        return repository.getAllUsers();
    }
}
