package com.github.tennyros.springbootjooq.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO, отражающий пользователя")
public record UserDto(
        @Schema(description = "ID пользователя") Long id,
        @Schema(description = "Username пользователя") String username,
        @Schema(description = "Email пользователя") String email
) {}
