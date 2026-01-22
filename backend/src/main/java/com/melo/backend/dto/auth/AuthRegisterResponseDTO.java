package com.melo.backend.dto.auth;

import com.melo.backend.entity.enums.UserRole;

public record AuthRegisterResponseDTO(
    String name,
    String email,
    UserRole role
) {}
