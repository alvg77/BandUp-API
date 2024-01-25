package com.bandup.api.controller;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.service.ArtistTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/artist-types")
@RequiredArgsConstructor
public class ArtistTypeController {
    private final ArtistTypeService artistTypeService;

    @GetMapping
    public ResponseEntity<Set<ArtistTypeDTO>> getUserTypes() {
        return ResponseEntity.ok(artistTypeService.getAll());
    }
}
