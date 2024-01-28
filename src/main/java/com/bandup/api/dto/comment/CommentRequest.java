package com.bandup.api.dto.comment;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CommentRequest {
    @NonNull
    private String content;
    private Long postId;
}
