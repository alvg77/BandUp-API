package com.bandup.api.service;

import com.bandup.api.dto.GenreDTO;

import java.util.Set;

public interface GenreService {
    Set<GenreDTO> getAll();
}
