package com.bandup.api.controller;

import com.bandup.api.dto.GenreDTO;
import com.bandup.api.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<Set<GenreDTO>> getGenres() {
        return ResponseEntity.ok(genreService.getAll());
    }
}
