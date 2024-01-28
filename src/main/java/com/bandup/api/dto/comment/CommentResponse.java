package com.bandup.api.dto.comment;

import com.bandup.api.dto.user.UserDetailResponse;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private Date createdAt;
    private UserDetailResponse creator;
}
