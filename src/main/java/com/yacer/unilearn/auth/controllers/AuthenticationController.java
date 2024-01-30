package com.yacer.unilearn.auth.controllers;

import com.yacer.unilearn.auth.pojos.AuthenticationRequest;
import com.yacer.unilearn.auth.pojos.RegistrationRequest;
import com.yacer.unilearn.auth.services.AuthenticationService;
import com.yacer.unilearn.security.pojos.AccessToken;
import com.yacer.unilearn.security.pojos.JwtToken;
import com.yacer.unilearn.security.pojos.RefreshTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yacer.unilearn.utils.ApplicationUtils.appLogger;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("login/")
    public ResponseEntity<JwtToken> authenticate(@RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("register/")
    public ResponseEntity<JwtToken> registerUser(@RequestBody RegistrationRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("refresh/")
    public ResponseEntity<AccessToken> generateAccessTokenFromRefreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        appLogger.info(refreshTokenDTO.toString());
        return ResponseEntity.ok(authService.generateAccessTokenFromRefreshToken(refreshTokenDTO));
    }


}
