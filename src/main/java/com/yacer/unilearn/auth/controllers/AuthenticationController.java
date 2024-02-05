package com.yacer.unilearn.auth.controllers;

import com.yacer.unilearn.auth.pojos.AuthenticationRequest;
import com.yacer.unilearn.auth.pojos.Message;
import com.yacer.unilearn.auth.pojos.RegistrationRequest;
import com.yacer.unilearn.auth.services.UserService;
import com.yacer.unilearn.config.pojos.AccessToken;
import com.yacer.unilearn.config.pojos.JwtToken;
import com.yacer.unilearn.config.pojos.RefreshTokenDTO;
import com.yacer.unilearn.config.pojos.Token;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yacer.unilearn.utils.ApplicationUtils.appLogger;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService authService;

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

    @PostMapping("verify/")
    @Operation(
            description = "This method takes a token and validate it , 200 fot valid and 401 for invalid",
            responses = {}
    )
    public ResponseEntity<Message> verifyToken(@RequestBody Token token) {
        var message = authService.validateToken(token.getToken());
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
