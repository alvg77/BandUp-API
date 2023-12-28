package com.bandup.api.service;

import com.bandup.api.dto.user.UserDTO;
import com.bandup.api.entity.User;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(Long id);
    public UserDTO getCurrentUser();
    public List<UserDTO> getUsersByUsername(String username);
    public UserDTO updateUser(UserDTO user);
    public void deleteUser();
}