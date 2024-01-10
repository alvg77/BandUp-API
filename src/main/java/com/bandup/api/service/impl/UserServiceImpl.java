package com.bandup.api.service.impl;

import com.bandup.api.dto.user.UserDTO;
import com.bandup.api.mapper.UserMapper;
import com.bandup.api.repository.UserRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public UserDTO getUserById(Long id) {
        return UserMapper.MAPPER.toUserDTO(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDTO getCurrentUser() {
        return UserMapper.MAPPER.toUserDTO(userRepository.findById(authService.getCurrentUser().getId()).orElseThrow());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return UserMapper.MAPPER.toUserDTO(userRepository.save(UserMapper.MAPPER.fromUserDTO(userDTO)));
    }

    @Override
    public void deleteUser() {
        userRepository.deleteById(authService.getCurrentUser().getId());
    }
}
