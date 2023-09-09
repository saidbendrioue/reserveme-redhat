package com.othex.reserveme.dto.auth;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String email;
    private String password;
}
