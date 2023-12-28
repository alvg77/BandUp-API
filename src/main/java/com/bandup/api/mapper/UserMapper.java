package com.bandup.api.mapper;

import com.bandup.api.dto.user.UserDTO;
import com.bandup.api.dto.user.UserDetailResponse;
import com.bandup.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location.id", ignore = true)
    User fromUserDTO(UserDTO userDTO);

    @Mapping(target = "id", source = "user.id")
    UserDTO toUserDTO(User user);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "profilePicture", source = "user.profilePicture")
    UserDetailResponse toUserDetailResponse(User user);

    @Mapping(target = "id", source = "user.id")
    List<UserDTO> toUserDTOs(List<User> users);
}
