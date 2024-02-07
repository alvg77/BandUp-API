package com.bandup.api.mapper;

import com.bandup.api.dto.user.UserDetailResponse;
import com.bandup.api.dto.user.UserResponse;
import com.bandup.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {LocationMapper.class, ContactsMapper.class})
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    UserResponse toUserDTO(User user);

    UserDetailResponse toUserDetailResponse(User user);
}
