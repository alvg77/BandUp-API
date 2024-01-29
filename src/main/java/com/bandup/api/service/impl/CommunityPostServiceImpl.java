package com.bandup.api.service.impl;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.User;
import com.bandup.api.exception.ForbiddenException;
import com.bandup.api.mapper.CommunityPostMapper;
import com.bandup.api.repository.CommunityPostRepository;
import com.bandup.api.repository.LikeRepository;
import com.bandup.api.repository.PostFlairRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.CommunityPostService;
import com.bandup.api.specification.CommunityPostSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityPostServiceImpl implements CommunityPostService {
    private final CommunityPostRepository communityPostRepository;
    private final AuthService authService;
    private final PostFlairRepository postFlairRepository;
    private final LikeRepository likeRepository;

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
                ).and(
                        CommunityPostSpecification.orderByCreatedAtDesc()
                );

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

        List<CommunityPost> posts = communityPostRepository.findAll(spec, pageRequest).getContent();
        List<CommunityPostResponse> responses = CommunityPostMapper.MAPPER.toCommunityPostResponses(posts);

        responses.forEach(response -> {
            response.setLiked(likeRepository.findByUserIdAndPostId(user.getId(), response.getId()).isPresent());
        });

        return responses;
    }

    @Override
    public CommunityPostResponse findById(Long id) {
        User user = authService.getCurrentUser();
        CommunityPost post = communityPostRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Community post not found")
        );
        CommunityPostResponse response = CommunityPostMapper.MAPPER.toCommunityPostResponse(post);
        response.setLiked(likeRepository.findByUserIdAndPostId(user.getId(), response.getId()).isPresent());

        return response;
    }

    @Override
    public CommunityPostResponse create(CommunityPostRequest request) {
        CommunityPost communityPost = CommunityPostMapper.MAPPER.fromCommunityPostRequest(request);

        communityPost.setUser(authService.getCurrentUser());
        communityPost.setFlair(postFlairRepository.findById(request.getFlairId()).orElseThrow(
                () -> new EntityNotFoundException("Flair not found")
        ));

        CommunityPostResponse response = CommunityPostMapper.MAPPER.toCommunityPostResponse(
                communityPostRepository.save(communityPost)
        );

        response.setLikeCount(0L);
        response.setLiked(false);

        return response;
    }

    @Override
    public CommunityPostResponse update(Long id, CommunityPostRequest request) {
        CommunityPost communityPost = communityPostRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Community post not found")
        );

        if (!communityPost.getUser().getId().equals(authService.getCurrentUser().getId())) {
            throw new ForbiddenException("You are not the owner of this post");
        }

        communityPost.setTitle(request.getTitle());
        communityPost.setContent(request.getContent());
        communityPost.setUrl(request.getUrl());
        communityPost.setFlair(postFlairRepository.findById(request.getFlairId()).orElseThrow(
                () -> new EntityNotFoundException("Flair not found")
        ));

        CommunityPostResponse response = CommunityPostMapper.MAPPER.toCommunityPostResponse(
                communityPostRepository.save(communityPost)
        );

        response.setLikeCount(likeRepository.countByPostId(response.getId()));
        response.setLiked(likeRepository.findByUserIdAndPostId(authService.getCurrentUser().getId(), response.getId()).isPresent());

        return response;
    }

    @Override
    public void deleteById(Long id) {
        communityPostRepository.deleteById(id);
    }
}
