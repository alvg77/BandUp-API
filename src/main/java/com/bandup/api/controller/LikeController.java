package com.bandup.api.controller;

import com.bandup.api.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> likePost(@RequestParam("postId") long postId) {
        likeService.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikePost(@RequestParam("postId") long postId) {
        likeService.unlikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
