package com.melo.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.melo.backend.dto.auth.AuthResponseDTO;
import com.melo.backend.dto.user.UserLoginDTO;
import com.melo.backend.dto.user.UserRegisterDTO;
import com.melo.backend.entity.User;
import com.melo.backend.repository.UserRepository;
import com.melo.backend.security.JwtService;

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

    public AuthResponseDTO login(UserLoginDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponseDTO(token);
    }
}
