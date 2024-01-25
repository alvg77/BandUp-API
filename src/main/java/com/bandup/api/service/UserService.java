package com.bandup.api.service;

import com.bandup.api.dto.user.UserDTO;

public interface UserService {
    public UserDTO getUserById(Long id);
    public UserDTO getCurrentUser();
    public UserDTO updateUser(UserDTO user);
    public void deleteUser();
}