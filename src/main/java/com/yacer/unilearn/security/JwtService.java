package com.yacer.unilearn.security;


import com.yacer.unilearn.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String ENCRYPTION_KEY = "321678f435b74c00c250fe7746fa19e5f3f3fd968396ceaf334edbe79df1a1f3";

    public String getEmail(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    public Claims parseToken(String token) {
        // This method creates a JWT parser and get the claims from the token
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token) {
        Claims claims = parseToken(token);
        Date expiresAt = claims.getExpiration();
        return expiresAt.before(new Date(System.currentTimeMillis() - 1000 * 60));
    }

    public Key getSignInKey() {
        // Convert text to byte array
        byte[] key = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1))
                .compact();
    }
}
