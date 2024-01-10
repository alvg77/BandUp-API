package com.bandup.api.service.impl;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.mapper.ArtistTypeMapper;
import com.bandup.api.repository.ArtistTypeRepository;
import com.bandup.api.service.ArtistTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtistTypeServiceImpl implements ArtistTypeService {
    private final ArtistTypeRepository artistTypeRepository;

    @Override
    public Set<ArtistTypeDTO> getAll() {
        return ArtistTypeMapper.MAPPER.toArtistTypeDTOs(
                new HashSet<>(artistTypeRepository.findAll())
        );
    }
}
