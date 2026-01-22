package com.melo.backend.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.melo.backend.entity.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    public String SECRET;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                                .withIssuer("space-shop-api")
                                .withSubject(user.getEmail())
                                .withExpiresAt(generateExpirationDate())
                                .sign(algorithm);
            
            return token;
        } catch (JWTCreationException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * 
     * @param token token
     * @return user email or null
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            
            return JWT.require(algorithm)
                        .withIssuer("space-shop-api")
                        .build()
                        .verify(token)
                        .getSubject(); 
        } catch (JWTVerificationException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
