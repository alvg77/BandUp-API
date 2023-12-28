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

    @Mapping(target = "postId", source = "communityPost.id")
    @Mapping(target = "creator.id", source = "comment.user.id")
    @Mapping(target = "creator.username", source = "comment.user.username")
    @Mapping(target = "creator.profilePicture", source = "comment.user.profilePicture")
    CommentResponse toCommentResponseResource(Comment comment);

    List<CommentResponse> toCommentResponses(List<Comment> commentList);
}
