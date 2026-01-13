package com.melo.backend.mappers;

import com.melo.backend.dto.user.UserRegisterDTO;
import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.entity.User;

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
