package com.bandup.api.mapper;

import com.bandup.api.dto.LocationDTO;
import com.bandup.api.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {
    LocationMapper MAPPER = Mappers.getMapper(LocationMapper.class);

    Location fromLocationDTO(LocationDTO locationDTO);
    LocationDTO toLocationDTO(Location location);
}
