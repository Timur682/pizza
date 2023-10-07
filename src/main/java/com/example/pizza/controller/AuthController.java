package com.example.pizza.controller;

import com.example.pizza.dto.SignInDto;
import com.example.pizza.dto.SignupDto;
import com.example.pizza.dto.UserRegistrationResponseDto;
import com.example.pizza.security.JwtTokenProvider;
import com.example.pizza.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@ResponseBody
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userService;

    @PostMapping(value = "/signin", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> signIn(@RequestBody SignInDto dto) {
        //Spring security object: username+password (principal+credentials)
        var authToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        //Spring security authManager checks the validity: (throws)
        authenticationManager.authenticate(authToken);

        //issue a JWT
        var jwt = tokenProvider.generateToken(dto.getUsername());

        return ResponseEntity.ok(Map.of("message", "Sign in successful", "token", jwt ));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponseDto>
    registerUser(@RequestBody @Valid SignupDto dto) {
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
    }
}
