package com.bandup.api.service;

import com.bandup.api.dto.comment.CommentRequest;
import com.bandup.api.dto.comment.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getAll();
    CommentResponse getById(Long id);
    CommentResponse create(CommentRequest request);
    CommentResponse update(Long id, CommentRequest request);
    void delete(Long id);
}
