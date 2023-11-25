package com.bandup.api.mapper;

import com.bandup.api.dto.GenreDTO;
import com.bandup.api.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {
    GenreMapper MAPPER = Mappers.getMapper(GenreMapper.class);

    Genre fromGenreDTO(GenreDTO genreDTO);
    GenreDTO toGenreDTO(Genre genre);
    List<GenreDTO> toGenreDTOs(List<Genre> genres);
}
