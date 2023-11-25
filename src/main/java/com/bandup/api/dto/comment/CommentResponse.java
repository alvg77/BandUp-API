package com.bandup.api.dto.comment;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private Date createdAt;
    private Long creatorId;
    private Long postId;
}
