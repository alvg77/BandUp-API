package com.bandup.api.mapper;

import com.bandup.api.dto.auth.RegisterRequest;
import com.bandup.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {LocationMapper.class, ContactsMapper.class})
public interface AuthMapper {
    AuthMapper MAPPER = Mappers.getMapper(AuthMapper.class);

    User fromRegisterRequestResource(RegisterRequest request);
}
