package com.bandup.api.controller;

import com.bandup.api.dto.auth.AuthResponse;
import com.bandup.api.dto.auth.LoginRequest;
import com.bandup.api.dto.auth.RegisterRequest;
import com.bandup.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        System.out.println("\n\nafsdf\n\n");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/available/email")
    public ResponseEntity<Void> verifyEmail(
            @RequestParam String email
    ) {
        authService.emailAvailability(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available/username")
    public ResponseEntity<Void> verifyUsername(
            @RequestParam String username
    ) {
        authService.usernameAvailability(username);
        return ResponseEntity.ok().build();
    }
}
