package com.bandup.api.service;

import com.bandup.api.dto.ArtistTypeDTO;

import java.util.Set;

public interface ArtistTypeService {
    Set<ArtistTypeDTO> getAll();
}
