package com.melo.backend.dto.user;

import com.melo.backend.entity.User;

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
