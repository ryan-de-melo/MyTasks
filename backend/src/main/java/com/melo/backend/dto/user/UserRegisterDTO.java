package com.melo.backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
    
    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,
    
    @NotBlank
    @Size(min = 8)
    String password
) {}
