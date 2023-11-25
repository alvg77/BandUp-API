package com.bandup.api.mapper;

import com.bandup.api.dto.UserLocationDTO;
import com.bandup.api.entity.UserLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserLocationMapper{
    UserLocationMapper MAPPER = Mappers.getMapper(UserLocationMapper.class);

    @Mapping(target = "id", ignore = true)
    UserLocation fromRegisterRequestResource(UserLocationDTO request);
    UserLocationDTO toAuthResponseResource(UserLocation user);
}
