package com.bandup.api.service.impl;

import com.bandup.api.dto.user.UserRequest;
import com.bandup.api.dto.user.UserResponse;
import com.bandup.api.entity.User;
import com.bandup.api.mapper.LocationMapper;
import com.bandup.api.mapper.UserMapper;
import com.bandup.api.repository.ArtistTypeRepository;
import com.bandup.api.repository.GenreRepository;
import com.bandup.api.repository.UserRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final ArtistTypeRepository artistTypeRepository;
    private final AuthService authService;


    @Override
    public UserResponse getUserById(Long id) {
        return UserMapper.MAPPER.toUserDTO(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserResponse getCurrentUser() {
        return UserMapper.MAPPER.toUserDTO(userRepository.findById(authService.getCurrentUser().getId()).orElseThrow());
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User user = authService.getCurrentUser();
        user.setUsername(userRequest.getUsername());
        user.setProfilePicture(userRequest.getProfilePicture());
        user.setBio(userRequest.getBio());
        user.setLocation(LocationMapper.MAPPER.fromLocationDTO(userRequest.getLocation()));
        user.setArtistType(
                artistTypeRepository.findById(userRequest.getArtistTypeId())
                        .orElseThrow(() -> new EntityNotFoundException("Artist type with such id does not exist!"))
        );
        user.setGenres(genreRepository.getGenresByIdIsIn(userRequest.getGenreIds()));

        return UserMapper.MAPPER.toUserDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser() {
        userRepository.deleteById(authService.getCurrentUser().getId());
    }
}
