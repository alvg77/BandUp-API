package com.bandup.api.service.impl;

import com.bandup.api.dto.GenreDTO;
import com.bandup.api.mapper.GenreMapper;
import com.bandup.api.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import com.bandup.api.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Set<GenreDTO> getAll() {
        return GenreMapper.MAPPER.toGenreDTOs(
                new HashSet<>(genreRepository.findAll())
        );
    }
}
