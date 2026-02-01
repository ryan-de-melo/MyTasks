package com.melo.backend.dto.user;

import java.io.Serializable;

import com.melo.backend.entity.User;

public record UserResponseDTO(
    String name,
    String email
) implements Serializable {
    public UserResponseDTO(User user) {
        this(user.getName(),
        user.getEmail()
        );
    }
}
