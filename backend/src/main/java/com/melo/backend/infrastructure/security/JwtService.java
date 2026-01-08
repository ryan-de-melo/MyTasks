package com.melo.backend.infrastructure.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.melo.backend.infrastructure.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public final String SECRET = "my-tasks-is-the-best-task-manager-out-there";  // this secret is for study purposes only

    public String generateToken(User user) {
        return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("userId", user.getId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .compact();
    }

}
