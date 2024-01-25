package com.bandup.api.service.impl;

import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.Like;
import com.bandup.api.entity.User;
import com.bandup.api.repository.CommunityPostRepository;
import com.bandup.api.repository.LikeRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final CommunityPostRepository communityPostRepository;
    private final AuthService authService;

    public void likePost(Long postId) {
        User user = authService.getCurrentUser();
        CommunityPost post = communityPostRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("No post with such id exists!")
        );

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);
    }

    public void unlikePost(Long postId) {
        User user = authService.getCurrentUser();
        Like like = likeRepository.findByUserIdAndPostId(user.getId(), postId).orElseThrow(
                () -> new EntityNotFoundException("No like with such id exists!")
        );
        likeRepository.delete(like);
    }
}
