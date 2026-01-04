package com.melo.backend.business;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.melo.backend.infrastructure.dto.user.UserRegisterDTO;
import com.melo.backend.infrastructure.model.User;
import com.melo.backend.infrastructure.repository.UserRepository;
import com.melo.backend.infrastructure.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder encoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public void register(UserRegisterDTO dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User
                    .builder()
                    .name(dto.name())
                    .email(dto.email())
                    .password(encoder.encode(dto.password()))
                    .build();

        userRepository.save(user);
    }

}
