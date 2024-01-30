package com.yacer.unilearn.security;


import com.yacer.unilearn.auth.repositories.RefreshTokenRepository;
import com.yacer.unilearn.auth.repositories.UserRepository;
import com.yacer.unilearn.entities.RefreshToken;
import com.yacer.unilearn.security.pojos.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.yacer.unilearn.utils.ApplicationUtils.appLogger;

@Component
@RequiredArgsConstructor
public class JwtService {
    @Value("${security.jwt.encryption.key}")
    private String ENCRYPTION_KEY;
    private static final long accessTokenDuration = 1000 * 60 * 60 * 2;
    private static final long refreshTokenDuration = 1000 * 60 * 60 * 24 * 15;
    private static final long oneHourDuration = 1000 * 60 * 60;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public String getEmail(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    public Claims parseToken(String token) {
        // This method creates a JWT parser and get the claims from the token
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isAccessTokenValid(String token) {
        Claims claims = parseToken(token);
        Date expiresAt = claims.getExpiration();
        var currentDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
        return currentDate.before(expiresAt);
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        var user = userRepository.findUserByRefreshToken(refreshToken).orElseThrow(() -> {
            appLogger.info("There is no user with this refresh token " + refreshToken);
            return new RuntimeException("There is no user with this refresh token " + refreshToken);
        });
        return user.getRefreshToken().getExpiresAt().after(new Date(System.currentTimeMillis() + oneHourDuration));
    }

    public Key getSignInKey() {
        // Convert text to byte array
        byte[] key = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    private String generateToken(UserDetails userDetails, long duration) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration))
                .signWith(getSignInKey())
                .compact();
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, accessTokenDuration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        var user = userRepository.findUserByEmail(userDetails.getUsername()).get();
        var refreshToken = refreshTokenRepository.findByUser(user).orElse(null);
        if (refreshToken == null) {
            // Create a new token
            refreshToken = RefreshToken.builder()
                    .token(UUID.randomUUID().toString())
                    .expiresAt(new Date(System.currentTimeMillis() + refreshTokenDuration))
                    .user(user)
                    .build();
        } else {
            // Update the current one
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiresAt(new Date(System.currentTimeMillis() + refreshTokenDuration));
        }
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public JwtToken generateJwtToken(UserDetails userDetails) {
        return new JwtToken(generateAccessToken(userDetails), generateRefreshToken(userDetails));
    }
}
