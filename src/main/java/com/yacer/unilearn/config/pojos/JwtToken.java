package com.yacer.unilearn.config.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {
    private String accessToken;
    private String refreshToken;
    private String role;
}
