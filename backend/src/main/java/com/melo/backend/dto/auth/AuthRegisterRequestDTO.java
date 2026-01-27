package com.melo.backend.dto.auth;

public record AuthRegisterRequestDTO(
    String name,
    String email,
    String password
) {}
