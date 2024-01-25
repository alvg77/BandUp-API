package com.bandup.api.mapper;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.entity.ArtistType;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface ArtistTypeMapper {
    ArtistTypeMapper MAPPER = org.mapstruct.factory.Mappers.getMapper(ArtistTypeMapper.class);

    ArtistTypeDTO toArtistTypeDTO(ArtistType artistType);
    Set<ArtistTypeDTO> toArtistTypeDTOs(Set<ArtistType> artistTypes);
}
