package com.melo.backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
    @NotBlank
    String name,

    @NotBlank
    @Size(min = 8)
    String password
) {}
