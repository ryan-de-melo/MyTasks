package com.melo.backend.dto.auth;

public record AuthLoginRequestDTO(
    String email,
    String password
) {}
