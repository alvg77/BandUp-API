package com.bandup.api.service.impl;

import com.bandup.api.dto.GenreDTO;
import com.bandup.api.mapper.GenreMapper;
import com.bandup.api.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import com.bandup.api.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<GenreDTO> getAll() {
        return GenreMapper.MAPPER.toGenreDTOs(
                genreRepository.findAll()
        );
    }
}
