package com.bandup.api.mapper;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.entity.ArtistType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ArtistTypeMapper {
    ArtistTypeMapper MAPPER = org.mapstruct.factory.Mappers.getMapper(ArtistTypeMapper.class);

    ArtistType fromArtistTypeDTO(ArtistTypeDTO artistTypeDTO);
    ArtistTypeDTO toArtistTypeDTO(ArtistType artistType);
    List<ArtistTypeDTO> toArtistTypeDTOs(List<ArtistType> artistTypes);
}
