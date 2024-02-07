package com.bandup.api.service.impl;

import com.bandup.api.dto.comment.CommentRequest;
import com.bandup.api.dto.comment.CommentResponse;
import com.bandup.api.entity.Comment;
import com.bandup.api.exception.ForbiddenException;
import com.bandup.api.mapper.CommentMapper;
import com.bandup.api.repository.CommentRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AuthService authService;

    @Override
    public List<CommentResponse> getAll(Long postId, Long pageNo, Long pageSize) {
        Pageable pageable = PageRequest.of(pageNo.intValue(), pageSize.intValue());
        return CommentMapper.MAPPER.toCommentResponses(
                commentRepository.findAllByCommunityPostIdOrderByCreatedAtDesc(postId, pageable)
        );
    }

    @Override
    public CommentResponse getById(Long id) {
        return CommentMapper.MAPPER.toCommentResponseResource(
                commentRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Comment not found")
                )
        );
    }

    @Override
    public CommentResponse create(CommentRequest request) {
        Comment comment = CommentMapper.MAPPER.fromCommentRequestResource(request);
        comment.setUser(authService.getCurrentUser());
        return CommentMapper.MAPPER.toCommentResponseResource(
                commentRepository.save(comment)
        );
    }

    @Override
    public CommentResponse update(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Comment not found")
        );

        if (!comment.getUser().getId().equals(authService.getCurrentUser().getId())) {
            throw new ForbiddenException("You are not the owner of this comment");
        }

        comment.setContent(request.getContent());

        return CommentMapper.MAPPER.toCommentResponseResource(
                commentRepository.save(comment)
        );
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
