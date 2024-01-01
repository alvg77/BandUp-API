package com.bandup.api.mapper;

import com.bandup.api.dto.LocationDTO;
import com.bandup.api.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserLocationMapper{
    UserLocationMapper MAPPER = Mappers.getMapper(UserLocationMapper.class);

    @Mapping(target = "id", ignore = true)
    Location fromRegisterRequestResource(LocationDTO request);
    LocationDTO toAuthResponseResource(Location user);
}
