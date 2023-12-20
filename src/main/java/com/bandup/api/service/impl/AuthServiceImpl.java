package com.bandup.api.service.impl;

import com.bandup.api.dto.auth.AuthResponse;
import com.bandup.api.dto.auth.LoginRequest;
import com.bandup.api.dto.auth.RegisterRequest;
import com.bandup.api.entity.Genre;
import com.bandup.api.entity.User;
import com.bandup.api.mapper.AuthMapper;
import com.bandup.api.repository.ArtistTypeRepository;
import com.bandup.api.repository.GenreRepository;
import com.bandup.api.repository.UserRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.JWTService;
import jakarta.persistence.EntityExistsException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final ArtistTypeRepository artistTypeRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            user = (User) auth.getPrincipal();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password!");
        }

        String jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);

        return authResponse;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = AuthMapper.MAPPER.fromRegisterRequestResource(registerRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Timestamp(new Date().getTime()));
        user.setArtistType(
                artistTypeRepository.findById(registerRequest.getArtistTypeId())
                .orElseThrow(() -> new EntityExistsException("Artist type with such id does not exist!"))
        );
        user.setGenres(genreRepository.getGenresByIdIsIn(registerRequest.getGenreIds()));

        String jwtToken = jwtService.generateToken(user);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException("User with such username/email already exists!");
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    @Override
    public void emailAvailability(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EntityExistsException("Email is already in use.");
        }
    }

    @Override
    public void usernameAvailability(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new EntityExistsException("Username is already in use.");
        }
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
