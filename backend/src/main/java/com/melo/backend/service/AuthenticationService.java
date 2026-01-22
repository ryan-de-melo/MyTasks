package com.melo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.melo.backend.dto.auth.AuthLoginRequestDTO;
import com.melo.backend.dto.auth.AuthLoginResponseDTO;
import com.melo.backend.entity.User;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthLoginResponseDTO login(AuthLoginRequestDTO dto) {
        UsernamePasswordAuthenticationToken usrPassToken = new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password());
        
        Authentication auth = authenticationManager.authenticate(usrPassToken);
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new AuthLoginResponseDTO(token);
    }

    public UserDetails getPrincipal() {
        UserDetails principal = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            principal = (UserDetails) auth.getPrincipal();
        }

        return principal;
    }

    public User getCurrentUser() {
        User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof User) {
            user = (User) auth.getPrincipal();
        }

        return user;
    }

}
