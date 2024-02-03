package com.yacer.unilearn.auth.pojos;

import com.yacer.unilearn.security.pojos.JwtToken;

public class AuthenticationResponse {
    private JwtToken tokens;
    private String role;
}
