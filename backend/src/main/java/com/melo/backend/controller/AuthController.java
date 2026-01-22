package com.melo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melo.backend.dto.auth.AuthLoginRequestDTO;
import com.melo.backend.dto.auth.AuthLoginResponseDTO;
import com.melo.backend.dto.auth.AuthRegisterRequestDTO;
import com.melo.backend.service.AuthenticationService;
import com.melo.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthRegisterRequestDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

}
