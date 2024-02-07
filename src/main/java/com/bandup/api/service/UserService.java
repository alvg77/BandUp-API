package com.bandup.api.service;

import com.bandup.api.dto.user.UserRequest;
import com.bandup.api.dto.user.UserResponse;

public interface UserService {
    public UserResponse getUserById(Long id);
    public UserResponse getCurrentUser();
    public UserResponse updateUser(UserRequest user);
    public void deleteUser();
}