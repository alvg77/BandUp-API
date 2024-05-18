package com.bandup.api.prototype;

import com.bandup.api.dto.comment.CommentRequest;
import com.bandup.api.entity.Comment;
import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import static com.bandup.api.prototype.UserPrototype.aUser;

public class CommentPrototype {
    private static long id = 1;

    public static Comment aComment(User user, CommunityPost communityPost) {
        return Comment.builder()
                .id(id++)
                .content("Content")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .user(user)
                .communityPost(communityPost)
                .build();
    }

    public static Comment aComment(User user, CommunityPost communityPost, String content) {
        return Comment.builder()
                .id(id++)
                .content(content)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .user(user)
                .communityPost(communityPost)
                .build();
    }

    public static CommentRequest aCommentRequest(Long postId) {
        return CommentRequest.builder()
                .postId(postId)
                .content("Content")
                .build();
    }
}
