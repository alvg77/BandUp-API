package com.bandup.api.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LikeDTO {
    @NonNull
    private Long postId;
}
