package com.bandup.api.mapper;

import com.bandup.api.dto.communitypost.CommunityPostRequest;
import com.bandup.api.dto.communitypost.CommunityPostResponse;
import com.bandup.api.entity.CommunityPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, PostFlairMapper.class})
public interface CommunityPostMapper {
    CommunityPostMapper MAPPER = Mappers.getMapper(CommunityPostMapper.class);

    CommunityPost fromCommunityPostRequest(CommunityPostRequest request);

    @Mapping(target = "likeCount", expression = "java((long) communityPost.getLikes().size())")
    @Mapping(target = "commentCount", expression = "java((long) communityPost.getComments().size())")
    @Mapping(target = "creator", source = "user")
    CommunityPostResponse toCommunityPostResponse(CommunityPost communityPost);

    List<CommunityPostResponse> toCommunityPostResponses(List<CommunityPost> communityPostList);
}
