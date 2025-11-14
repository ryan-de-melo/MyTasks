package com.melo.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
    @NotBlank
    @NotNull
    @NotEmpty
    String name,

    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    String email
) {}
