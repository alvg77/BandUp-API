package com.bandup.api.controller;

import com.bandup.api.dto.advertisement.AdvertisementRequest;
import com.bandup.api.dto.advertisement.AdvertisementResponse;
import com.bandup.api.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @GetMapping
    public ResponseEntity<List<AdvertisementResponse>> getAll() {
        return ResponseEntity.ok(advertisementService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AdvertisementResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdvertisementResponse> create(@RequestBody AdvertisementRequest request) {
        return ResponseEntity.ok(advertisementService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<AdvertisementResponse> update(@PathVariable Long id, @RequestBody AdvertisementRequest request) {
        return ResponseEntity.ok(advertisementService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        advertisementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
