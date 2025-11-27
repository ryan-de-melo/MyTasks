package com.melo.backend.dto.user;

public record UserResponseDTO(
    Long id,
    String name,
    String email
) {}
