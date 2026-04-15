package com.nikhil.jobtracker.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "my-secret-key-my-secret-key-my-secret-key"; // min 32 chars
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // payload (who is user)
                .setIssuedAt(new Date()) // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // expiry
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // signing
                .compact();
    }

    // Extract Email (username)
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    //  Validate Token
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    //extract all claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // check expiry
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}