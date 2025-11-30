package com.melo.backend.infrastructure.dto.mappers;

import com.melo.backend.infrastructure.dto.user.UserRegisterDTO;
import com.melo.backend.infrastructure.dto.user.UserResponseDTO;
import com.melo.backend.infrastructure.model.User;

public class UserMapper {

    public static User toEntity(UserRegisterDTO dto) {
        return User.builder()
                    .name(dto.name())
                    .email(dto.email())
                    .password(dto.password())
                    .build();
    }

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getName(), user.getEmail());
    }

}
