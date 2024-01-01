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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "flair.id", ignore = true)
    CommunityPost fromCommunityPostRequest(CommunityPostRequest request);

    @Mapping(target = "commentCount", expression = "java((long) communityPost.getComments().size())")
    @Mapping(target = "creator.id", source = "communityPost.user.id")
    CommunityPostResponse toCommunityPostResponse(CommunityPost communityPost);

    List<CommunityPostResponse> toCommunityPostResponses(List<CommunityPost> communityPostList);
}
