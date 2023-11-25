package com.bandup.api.controller;

import com.bandup.api.dto.PostFlairDTO;
import com.bandup.api.service.PostFlairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post-flairs")
@RequiredArgsConstructor
public class PostFlairController {
    private final PostFlairService postFlairService;

    @GetMapping
    public ResponseEntity<List<PostFlairDTO>> getPostFlairs() {
        return ResponseEntity.ok(postFlairService.getAll());
    }
}
