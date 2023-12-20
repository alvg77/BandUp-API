package com.bandup.api.service;

import com.bandup.api.dto.auth.AuthResponse;
import com.bandup.api.dto.auth.LoginRequest;
import com.bandup.api.dto.auth.RegisterRequest;
import com.bandup.api.entity.User;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(RegisterRequest registerRequest);
    void emailAvailability(String email);
    void usernameAvailability(String username);
    User getCurrentUser();
}
