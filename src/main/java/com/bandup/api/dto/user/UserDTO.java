package com.bandup.api.dto.user;

import com.bandup.api.dto.ContactsDTO;
import com.bandup.api.dto.LocationDTO;
import com.bandup.api.dto.MediaDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String profileBanner;
    private String bio;
    private LocationDTO location;
    private ContactsDTO contacts;
    private List<MediaDTO> media;
    private Timestamp createdAt;
}
