package com.melo.backend.infrastructure.dto.user;

import com.melo.backend.infrastructure.model.User;

public record UserResponseDTO(
    String name,
    String email
) {
    public UserResponseDTO(User user) {
        this(user.getName(),
        user.getEmail()
        );
    }
}
