package com.github.tennyros.springbootjooq.service;

import com.github.tennyros.springbootjooq.repository.UserRepository;
import com.github.tennyros.tables.records.AppUserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public AppUserRecord addUser(String username, String email) {
        AppUserRecord newUser = new AppUserRecord();
        newUser.setUsername(username);
        newUser.setEmail(email);

        return repository.createUser(newUser);
    }

    public List<AppUserRecord> listUsers() {
        return repository.getAllUsers();
    }
}
