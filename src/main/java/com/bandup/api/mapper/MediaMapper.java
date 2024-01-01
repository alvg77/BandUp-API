package com.bandup.api.mapper;

import com.bandup.api.dto.MediaDTO;
import com.bandup.api.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MediaMapper {
    MediaMapper MAPPER = Mappers.getMapper(MediaMapper.class);

    @Mapping(target = "id", ignore = true)
    Media fromContactsDTO(MediaDTO media);
    MediaDTO toContactsDTO(Media contacts);

    List<MediaDTO> toMediaDTOList(List<Media> mediaList);
}
