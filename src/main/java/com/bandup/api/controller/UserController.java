package com.bandup.api.controller;

import com.bandup.api.dto.user.UserDTO;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "username", required = false) Optional<String> username) {
        return ResponseEntity.ok(userService.getAllUsers(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }
}
