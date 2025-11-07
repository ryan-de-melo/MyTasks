package com.melo.backend.infrastructure;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterDTO {
    
    private String name;

    @Email
    private String email;
    
    private String password;
}
