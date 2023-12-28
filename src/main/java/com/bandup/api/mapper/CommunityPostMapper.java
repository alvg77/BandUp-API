package com.bandup.api.mapper;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.dto.user.UserDetailResponse;
import com.bandup.api.entity.CommunityPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommunityPostMapper {
    CommunityPostMapper MAPPER = Mappers.getMapper(CommunityPostMapper.class);

    CommunityPost fromCommunityPostRequest(CommunityPostRequest request);

    @Mapping(target = "creator.id", source = "communityPost.user.id")
    @Mapping(target = "creator.username", source = "communityPost.user.username")
    @Mapping(target = "creator.profilePicture", source = "communityPost.user.profilePicture")
    CommunityPostResponse toCommunityPostResponse(CommunityPost communityPost);
    List<CommunityPostResponse> toCommunityPostResponses(List<CommunityPost> communityPostList);
}
