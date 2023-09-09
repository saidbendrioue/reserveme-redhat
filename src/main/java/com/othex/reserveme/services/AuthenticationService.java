package com.othex.reserveme.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.othex.reserveme.dto.UserDTO;
import com.othex.reserveme.dto.auth.JwtAuthenticationRequest;
import com.othex.reserveme.dto.auth.JwtAuthenticationResponse;
import com.othex.reserveme.mappers.UserMapper;
import com.othex.reserveme.repositories.UserRepository;
import com.othex.reserveme.security.JwtService;

import io.jsonwebtoken.security.InvalidKeyException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserDTO signUp(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        var userDao = userMapper.toDao(userDTO);
        var savedUser = userRepository.save(userDao);
        return userMapper.toDto(savedUser);
    }

    public JwtAuthenticationResponse signIn(JwtAuthenticationRequest jwtAuthenticationRequest) throws InvalidKeyException, NoSuchAlgorithmException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getEmail(), jwtAuthenticationRequest.getPassword()));
        var user = userRepository.findByEmail(jwtAuthenticationRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var userDTO = userMapper.toDto(user);
        return JwtAuthenticationResponse.builder().token(jwt).user(userDTO).build();
    }
}
