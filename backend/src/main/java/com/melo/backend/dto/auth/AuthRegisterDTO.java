package com.melo.backend.dto.auth;

import com.melo.backend.entity.enums.UserRole;

public record AuthRegisterDTO(
    String name,
    String email,
    String password,
    UserRole role
) {}
