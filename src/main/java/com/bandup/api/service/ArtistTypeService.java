package com.bandup.api.service;

import com.bandup.api.dto.ArtistTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArtistTypeService {
    List<ArtistTypeDTO> getAll();
}
