package com.melo.backend.infrastructure.dto.user;

public record UserResponseDTO(
    Long id,
    String name,
    String email
) {}
