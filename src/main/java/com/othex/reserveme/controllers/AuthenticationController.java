package com.othex.reserveme.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.othex.reserveme.dto.UserDTO;
import com.othex.reserveme.dto.auth.JwtAuthenticationRequest;
import com.othex.reserveme.dto.auth.JwtAuthenticationResponse;
import com.othex.reserveme.services.AuthenticationService;

import io.jsonwebtoken.security.InvalidKeyException;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO user) throws InvalidKeyException, NoSuchAlgorithmException {
        var response = authenticationService.signUp(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(
            @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) throws InvalidKeyException, NoSuchAlgorithmException {
        var response = authenticationService.signIn(jwtAuthenticationRequest);
        return ResponseEntity.ok(response);
    }
}
