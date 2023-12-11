package com.bandup.api.mapper;

import com.bandup.api.dto.auth.AuthResponse;
import com.bandup.api.dto.auth.RegisterRequest;
import com.bandup.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper MAPPER = Mappers.getMapper(AuthMapper.class);

    @Mapping(target = "location.id", ignore = true)
    @Mapping(target = "communityPosts", ignore = true)
    @Mapping(target = "artistType.id", source = "artistTypeId")
    User fromRegisterRequestResource(RegisterRequest request);
}
