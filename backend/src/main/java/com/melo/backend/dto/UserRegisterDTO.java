package com.melo.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
    
    @NotBlank
    @NotNull
    @NotEmpty
    String name,

    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    String email,
    
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 8)
    String password
) {}
