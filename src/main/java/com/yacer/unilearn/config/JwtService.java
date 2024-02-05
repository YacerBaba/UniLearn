package com.yacer.unilearn.config;


import com.yacer.unilearn.auth.repositories.RefreshTokenRepository;
import com.yacer.unilearn.auth.repositories.UserRepository;
import com.yacer.unilearn.entities.RefreshToken;
import com.yacer.unilearn.config.pojos.JwtToken;
import com.yacer.unilearn.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static com.yacer.unilearn.utils.ApplicationUtils.appLogger;

@Component
@RequiredArgsConstructor
public class JwtService {
    @Value("${security.jwt.encryption.key}")
    private String ENCRYPTION_KEY;
    private static final long oneHourDuration = 1000 * 60 * 60;
    private static final long accessTokenDuration = oneHourDuration * 3;
    private static final long refreshTokenDuration = oneHourDuration * 24 * 15;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public String getEmail(String token) {
        Claims claims = parseToken(token);
        if (claims != null)
            return claims.getSubject();
        return null;
    }

    public Claims parseToken(String token) {
        // This method creates a JWT parser and get the claims from the token
        try {
            return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return null;
        } catch (MalformedJwtException exception) {
            return null;
        }
    }

    public boolean isAccessTokenValid(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            Date expiresAt = claims.getExpiration();
            var currentDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
            return currentDate.before(expiresAt);
        }
        return false;
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        var user = userRepository.findUserByRefreshToken(refreshToken).orElse(null);
        if (user != null)
            return user.getRefreshToken().getExpiresAt().after(new Date(System.currentTimeMillis() + oneHourDuration));
        return false;
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

    public JwtToken generateJwtToken(User user) {
        var role = user.getRole().getName();
        return new JwtToken(generateAccessToken(user), generateRefreshToken(user), role);
    }
}
