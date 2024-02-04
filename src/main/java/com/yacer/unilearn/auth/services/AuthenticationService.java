package com.yacer.unilearn.auth.services;

import com.yacer.unilearn.auth.pojos.AuthenticationRequest;
import com.yacer.unilearn.auth.pojos.Message;
import com.yacer.unilearn.auth.pojos.RegistrationRequest;
import com.yacer.unilearn.auth.repositories.AuthorityRepository;
import com.yacer.unilearn.auth.repositories.UserRepository;
import com.yacer.unilearn.config.pojos.Token;
import com.yacer.unilearn.entities.User;
import com.yacer.unilearn.config.JwtService;
import com.yacer.unilearn.config.pojos.AccessToken;
import com.yacer.unilearn.config.pojos.JwtToken;
import com.yacer.unilearn.config.pojos.RefreshTokenDTO;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.yacer.unilearn.utils.ApplicationUtils.appLogger;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public JwtToken authenticate(AuthenticationRequest request) {
        appLogger.info("Trying to authenticate " + request.getEmail());
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        if (authentication.isAuthenticated()) {
            appLogger.info(request.getEmail() + " is authenticated");
            // generate token
            var user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            return jwtService.generateJwtToken(user);
        }
        return null;
    }

    public JwtToken registerUser(RegistrationRequest request) {
        var authority = authorityRepository.findByAuthorityEquals("STUDENT").get();
        var authorities = List.of(authority);
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountNonExpired(true)
                .enabled(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .authorities(authorities)
                .birthday(LocalDateTime.now()).dateJoined(LocalDateTime.now()).build();
        userRepository.save(user);
        return jwtService.generateJwtToken(user);
    }


    public AccessToken generateAccessTokenFromRefreshToken(RefreshTokenDTO token) {
        // Verify refresh token expiration date
        var refreshToken = token.getRefreshToken();
        if (jwtService.isRefreshTokenValid(refreshToken)) {
            var user = userRepository.findUserByRefreshToken(refreshToken).get();
            var newAccessToken = jwtService.generateAccessToken(user);
            return new AccessToken(newAccessToken);
        }
        return null;
    }


    private User getUserByRefreshToken(String refreshToken) {
        return userRepository.findUserByRefreshToken(refreshToken)
                .orElseThrow(() -> {
                    appLogger.info("This Username has not a refresh token");
                    return new UsernameNotFoundException("This Username has not a refresh token");
                });
    }

    public Message validateToken(String token) {
        if (jwtService.isAccessTokenValid(token)) {
            return new Message(HttpStatus.OK, "Token is valid");
        } else if (jwtService.isRefreshTokenValid(token)) {
            return new Message(HttpStatus.OK, "Token is valid");
        } else
            return new Message(HttpStatus.UNAUTHORIZED, "Token is invalid or expired");
    }
}
