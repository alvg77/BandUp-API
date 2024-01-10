package com.bandup.api.service;

import com.bandup.api.dto.ArtistTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface ArtistTypeService {
    Set<ArtistTypeDTO> getAll();
}
