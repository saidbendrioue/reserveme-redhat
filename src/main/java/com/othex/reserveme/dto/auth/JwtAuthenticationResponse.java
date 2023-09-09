package com.othex.reserveme.dto.auth;

import com.othex.reserveme.dto.UserDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthenticationResponse {
    private UserDTO user;
    private String token;
}
