package com.bandup.api.controller;

import com.bandup.api.dto.comment.CommentRequest;
import com.bandup.api.dto.comment.CommentResponse;
import com.bandup.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAll(
            @RequestParam(value = "postId", required = true) Long postId
    ) {
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
