package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {

    private final String secret = "your-256-bit-secret-your-256-bit-secret"; // must be at least 256-bit
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());
    private final long expiration = 1000 * 60 * 60; // 1 hour

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Get claims
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    // Generate token
    public String generateToken(String username, Long userId, Set<String> roles) {
        return Jwts.builder()
                   .setSubject(username)
                   .claim("userId", userId)
                   .claim("roles", roles)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expiration))
                   .signWith(key, SignatureAlgorithm.HS256)
                   .compact();
    }

    // Optional: get username
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }
}
