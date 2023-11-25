package com.bandup.api.service.impl;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.entity.CommunityPost;
import com.bandup.api.mapper.CommunityPostMapper;
import com.bandup.api.repository.CommunityPostRepository;
import com.bandup.api.repository.PostFlairRepository;
import com.bandup.api.service.AuthService;
import com.bandup.api.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityPostServiceImpl implements CommunityPostService {

    private final CommunityPostRepository communityPostRepository;
    private final AuthService authService;
    private final PostFlairRepository postFlairRepository;

    @Override
    public List<CommunityPostResponse> findAll() {
        return CommunityPostMapper.MAPPER.toCommunityPostResponses(communityPostRepository.findAll());
    }

    @Override
    public CommunityPostResponse findById(Long id) {
        return CommunityPostMapper.MAPPER.toCommunityPostResponse(communityPostRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Community post not found")
        ));
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

        if (communityPost.getUser().getId() != authService.getCurrentUser().getId()) {
            throw new RuntimeException("You are not the owner of this post");
        }

        communityPost.setTitle(request.getTitle());
        communityPost.setContent(request.getContent());
        communityPost.setStaticMediaKey(request.getStaticMediaKey());
        communityPost.setFlair(postFlairRepository.findById(request.getFlairId()).orElseThrow(
                () -> new RuntimeException("Flair not found")
        ));

        return CommunityPostMapper.MAPPER.toCommunityPostResponse(
                communityPostRepository.save(communityPost)
        );
    }

    @Override
    public void deleteById(Long id) {
        communityPostRepository.deleteById(id);
    }
}
