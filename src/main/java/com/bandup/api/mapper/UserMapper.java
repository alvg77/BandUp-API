package com.bandup.api.mapper;

import com.bandup.api.dto.user.UserDTO;
import com.bandup.api.dto.user.UserDetailResponse;
import com.bandup.api.entity.Contacts;
import com.bandup.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {LocationMapper.class, ContactsMapper.class})
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User fromUserDTO(UserDTO userDTO);
    @Mapping(target = "id", source = "user.id")
    UserDTO toUserDTO(User user);
    UserDetailResponse toUserDetailResponse(User user);
}
