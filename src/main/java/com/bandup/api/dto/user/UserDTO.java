package com.bandup.api.dto.user;

import com.bandup.api.dto.UserLocationDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String profileBanner;
    private String bio;
    private UserLocationDTO location;
    private Timestamp createdAt;
}
