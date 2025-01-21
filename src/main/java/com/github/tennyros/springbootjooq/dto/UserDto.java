package com.github.tennyros.springbootjooq.dto;

public record UserDto(
        Long id,
        String username,
        String email
) { }
