package com.bandup.api.controller;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/community-posts")
@RequiredArgsConstructor
public class CommunityPostController {

    private final CommunityPostService communityPostService;
    @GetMapping
    public ResponseEntity<List<CommunityPostResponse>> getAll() {
        return ResponseEntity.ok(communityPostService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityPostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(communityPostService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CommunityPostResponse> create(@RequestBody CommunityPostRequest request) {
        return ResponseEntity.ok(communityPostService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityPostResponse> update(@PathVariable Long id, @RequestBody CommunityPostRequest request) {
        return ResponseEntity.ok(communityPostService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        communityPostService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
