package com.bandup.api.dto.communitypost;

import com.bandup.api.dto.PostFlairDTO;
import com.bandup.api.dto.user.UserDetailResponse;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
public class CommunityPostResponse {
    private Long id;
    private String title;
    @Nullable
    private String url;
    private String content;
    private PostFlairDTO flair;
    private UserDetailResponse creator;
    private Timestamp createdAt;
}
