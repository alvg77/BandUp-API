package com.bandup.api.mapper;

import com.bandup.api.dto.GenreDTO;
import com.bandup.api.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface GenreMapper {
    GenreMapper MAPPER = Mappers.getMapper(GenreMapper.class);

    GenreDTO toGenreDTO(Genre genre);
    Set<GenreDTO> toGenreDTOs(Set<Genre> genres);
}
