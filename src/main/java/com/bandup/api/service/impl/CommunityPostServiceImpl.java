package com.bandup.api.service.impl;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.User;
import com.bandup.api.mapper.CommunityPostMapper;
import com.bandup.api.repository.CommunityPostRepository;
import com.bandup.api.repository.PostFlairRepository;
import com.bandup.api.repository.UserRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.CommunityPostService;
import com.bandup.api.specification.CommunityPostSpecification;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class CommunityPostServiceImpl implements CommunityPostService {
    private final CommunityPostRepository communityPostRepository;
    private final AuthService authService;
    private final PostFlairRepository postFlairRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommunityPostResponse> findAll(
            Integer pageNo,
            Integer pageSize,
            String search,
            Long flairId,
            Long userId
    ) {
        User user = authService.getCurrentUser();

        Specification<CommunityPost> spec = Specification
                .where(
                        search != null ? CommunityPostSpecification.search(search) : null
                ).and(
                        flairId != null ? CommunityPostSpecification.hasFlairIdEqual(flairId) : null
                ).and(
                        userId != null ? CommunityPostSpecification.hasUserIdEqual(userId) : null
                );

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        List<CommunityPost> posts = communityPostRepository.findAll(spec, pageRequest).getContent();


        List<CommunityPostResponse> postResponses = CommunityPostMapper.MAPPER.toCommunityPostResponses(posts);

        posts.forEach(
            post -> {
                postResponses.get(posts.indexOf(post)).setLiked(post.getLikedByUsers().contains(user));
                postResponses.get(posts.indexOf(post)).setDisliked(post.getDislikedByUsers().contains(user));
            }
        );

        return postResponses;
    }

    @Override
    public CommunityPostResponse findById(Long id) {
        User user = authService.getCurrentUser();
        CommunityPost post = communityPostRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Community post not found")
        );
        CommunityPostResponse response = CommunityPostMapper.MAPPER.toCommunityPostResponse(post);

        response.setLiked(post.getLikedByUsers().contains(user));
        response.setDisliked(post.getDislikedByUsers().contains(user));

        return response;
    }

    @Override
    public CommunityPostResponse create(CommunityPostRequest request) {
        CommunityPost communityPost = CommunityPostMapper.MAPPER.fromCommunityPostRequest(request);

        communityPost.setUser(authService.getCurrentUser());
        communityPost.setFlair(postFlairRepository.findById(request.getFlairId()).orElseThrow(
                () -> new RuntimeException("Flair not found")
        ));

        return CommunityPostMapper.MAPPER.toCommunityPostResponse(
                communityPostRepository.save(communityPost)
        );
    }

    @Override
    public CommunityPostResponse update(Long id, CommunityPostRequest request) {
        CommunityPost communityPost = communityPostRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Community post not found")
        );

        if (!communityPost.getUser().getId().equals(authService.getCurrentUser().getId())) {
            throw new RuntimeException("You are not the owner of this post");
        }

        communityPost.setTitle(request.getTitle());
        communityPost.setContent(request.getContent());
        communityPost.setUrl(request.getUrl());
        communityPost.setFlair(postFlairRepository.findById(request.getFlairId()).orElseThrow(
                () -> new RuntimeException("Flair not found")
        ));

        return CommunityPostMapper.MAPPER.toCommunityPostResponse(
                communityPostRepository.save(communityPost)
        );
    }

    public void likePost(Long id) {
        CommunityPost communityPost = communityPostRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Community post not found")
        );
        User user = authService.getCurrentUser();

        if (communityPost.getLikedByUsers().contains(user)) {
            communityPost.getLikedByUsers().remove(user);
            user.getLikedPosts().remove(communityPost);
        } else {
            communityPost.getLikedByUsers().add(user);
            user.getLikedPosts().add(communityPost);
        }

        userRepository.save(user);
        communityPostRepository.save(communityPost);
    }

    public void dislikePost(Long id) {
        CommunityPost communityPost = communityPostRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Community post not found")
        );
        User user = authService.getCurrentUser();

        if (communityPost.getDislikedByUsers().contains(user)) {
            communityPost.getDislikedByUsers().remove(user);
            user.getDislikedPosts().remove(communityPost);
        } else {
            communityPost.getDislikedByUsers().add(user);
            user.getDislikedPosts().add(communityPost);
        }

        userRepository.save(user);
        communityPostRepository.save(communityPost);
    }

    @Override
    public void deleteById(Long id) {
        communityPostRepository.deleteById(id);
    }
}
