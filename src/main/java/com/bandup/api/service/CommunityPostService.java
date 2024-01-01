package com.bandup.api.service;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;

import java.util.List;

public interface CommunityPostService {
    List<CommunityPostResponse> findAll(
            String search,
            Long flairId,
            Long userId
    );
    CommunityPostResponse findById(Long id);
    CommunityPostResponse create(CommunityPostRequest request);
    CommunityPostResponse update(Long id, CommunityPostRequest request);
    void deleteById(Long id);
}
