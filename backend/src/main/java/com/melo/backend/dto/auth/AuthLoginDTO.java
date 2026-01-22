package com.melo.backend.dto.auth;

public record AuthLoginDTO(
    String email,
    String password
) {}
