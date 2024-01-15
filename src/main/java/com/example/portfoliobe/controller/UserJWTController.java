package com.example.portfoliobe.controller;

import com.example.portfoliobe.dto.TokenRequest;
import com.example.portfoliobe.dto.TokenResponse;
import com.example.portfoliobe.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserJWTController {
    private final JwtUtils jwtUtils;

    public UserJWTController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/log-in")
    public ResponseEntity<TokenResponse> login(@RequestBody TokenRequest request) throws Exception {
        byte[] requestBytes = convertObjectToBytes(request);
        String jwt = jwtUtils.generateToken();
        return ResponseEntity.ok().body(new TokenResponse("Bear ", jwt, String.valueOf(jwtUtils.getProperties().getExpiresIn())));
    }
    private byte[] convertObjectToBytes(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(object);
    }
}
