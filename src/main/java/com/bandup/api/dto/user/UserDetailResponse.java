package com.bandup.api.dto.user;

import lombok.Data;

@Data
public class UserDetailResponse {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
}
