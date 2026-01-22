package com.melo.backend.dto.auth;

import com.melo.backend.entity.enums.UserRole;

public record AuthRegisterRequestDTO(
    String name,
    String email,
    String password,
    UserRole role
) {}
