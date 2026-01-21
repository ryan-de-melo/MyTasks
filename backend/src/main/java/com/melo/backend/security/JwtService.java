package com.melo.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.melo.backend.entity.User;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    public String SECRET;

    public String generateToken(User user) {
        return null;
    }

    public boolean isTokenValid(String token) {
        return true;
    }

}
