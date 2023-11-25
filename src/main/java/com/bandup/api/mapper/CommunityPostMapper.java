package com.bandup.api.mapper;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.entity.CommunityPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommunityPostMapper {
    CommunityPostMapper MAPPER = Mappers.getMapper(CommunityPostMapper.class);

    CommunityPost fromCommunityPostRequest(CommunityPostRequest request);

    @Mapping(target = "creatorId", source = "user.id")
    CommunityPostResponse toCommunityPostResponse(CommunityPost communityPost);
    List<CommunityPostResponse> toCommunityPostResponses(List<CommunityPost> communityPostList);
}
