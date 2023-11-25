package com.bandup.api.mapper;

import com.bandup.api.dto.comment.CommentRequest;
import com.bandup.api.dto.comment.CommentResponse;
import com.bandup.api.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "communityPost.id", source = "postId")
    Comment fromCommentRequestResource(CommentRequest request);

    @Mapping(target = "creatorId", source = "user.id")
    @Mapping(target = "postId", source = "communityPost.id")
    CommentResponse toCommentResponseResource(Comment comment);

    List<CommentResponse> toCommentResponses(List<Comment> commentList);
}
