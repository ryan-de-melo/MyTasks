package com.melo.backend.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.melo.backend.entity.User;

import io.jsonwebtoken.Claims;
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

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> solver) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

        return solver.apply(claims);
    }

}
