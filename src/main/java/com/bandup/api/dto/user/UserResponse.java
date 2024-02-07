package com.bandup.api.dto.user;

import com.bandup.api.dto.ContactsDTO;
import com.bandup.api.dto.LocationDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String profileBanner;
    private String bio;
    private LocationDTO location;
    private ContactsDTO contacts;
    private Timestamp createdAt;
}
