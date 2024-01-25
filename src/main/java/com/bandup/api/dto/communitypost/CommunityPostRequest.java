package com.bandup.api.dto.communitypost;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunityPostRequest {
    private String title;
    @Nullable
    private String url;
    private String content;
    private Long flairId;
}
