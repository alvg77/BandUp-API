package com.bandup.api.dto.communitypost;

import com.bandup.api.dto.PostFlairDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
public class CommunityPostResponse {
    private Long id;
    private String title;
    private String content;
    private PostFlairDTO flair;
    private String staticMediaKey;
    private Long creatorId;
    private Timestamp createdAt;
}
