package com.bandup.api.service.impl;

import com.bandup.api.dto.comment.CommentRequest;
import com.bandup.api.dto.comment.CommentResponse;
import com.bandup.api.entity.Comment;
import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.User;
import com.bandup.api.exception.ForbiddenException;
import com.bandup.api.repository.CommentRepository;
import com.bandup.api.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bandup.api.prototype.CommentPrototype.aComment;
import static com.bandup.api.prototype.CommentPrototype.aCommentRequest;
import static com.bandup.api.prototype.CommunityPostPrototype.aCommunityPost;
import static com.bandup.api.prototype.UserPrototype.aUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private final CommentRepository mockCommentRepository = Mockito.mock(CommentRepository.class);
    @Mock
    private final AuthService mockAuthService = Mockito.mock(AuthService.class);

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getAll() {
        User user = aUser();
        CommunityPost post = aCommunityPost(user);

        List<Comment> mockComments = new ArrayList<>();
        mockComments.add(aComment(user, post));
        mockComments.add(aComment(user, post));

        when(mockCommentRepository.findAllByCommunityPostIdOrderByCreatedAtDesc(eq(post.getId()), any(Pageable.class)))
                .thenReturn(mockComments);

        Long pageNo = 1L, pageSize = 10L;
        List<CommentResponse> result = commentService.getAll(post.getId(), pageNo, pageSize);

        assertEquals(2, result.size());
    }

    @Test
    void testCreateCommentSuccess() {
        User user = aUser();
        CommunityPost post = aCommunityPost(user);
        CommentRequest request = aCommentRequest(post.getId());
        Comment savedComment = aComment(user, post, request.getContent());

        when(mockAuthService.getCurrentUser()).thenReturn(user);
        when(mockCommentRepository.save(any(Comment.class))).thenReturn(savedComment);

        CommentResponse response = commentService.create(request);

        assertEquals(savedComment.getId(), response.getId());
        assertEquals(savedComment.getContent(), response.getContent());
    }


    @Test
    void testUpdateCommentSuccess() {
        User user = aUser();
        CommunityPost post = aCommunityPost(user);
        Comment comment = aComment(user, post);
        Long commentId = comment.getId();

        CommentRequest request = aCommentRequest(post.getId());
        comment.setContent(request.getContent());

        when(mockAuthService.getCurrentUser()).thenReturn(user);
        when(mockCommentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(mockCommentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentResponse response = commentService.update(commentId, request);

        assertEquals(commentId, response.getId());
        assertEquals(request.getContent(), response.getContent());
    }

    @Test
    void testUpdateCommentNotFound() {
        Long commentId = 123L;
        Long postId = 1L;

        when(mockCommentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> commentService.update(commentId, aCommentRequest(postId)));
    }

    @Test
    void testUpdateCommentForbidden() {
        User user = aUser();
        User anotherUser = aUser();
        Comment comment = aComment(user, aCommunityPost(user));
        Long commentId = comment.getId();
        Long postId = 1L;

        when(mockAuthService.getCurrentUser()).thenReturn(anotherUser);
        when(mockCommentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        assertThrows(ForbiddenException.class, () -> commentService.update(commentId, aCommentRequest(postId)));
    }
}