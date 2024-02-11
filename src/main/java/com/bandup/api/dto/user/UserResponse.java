package com.bandup.api.dto.user;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.dto.ContactsDTO;
import com.bandup.api.dto.GenreDTO;
import com.bandup.api.dto.LocationDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String profilePictureKey;
    private String bio;
    private Set<GenreDTO> genres;
    private ArtistTypeDTO artistType;
    private LocationDTO location;
    private ContactsDTO contacts;
    private Timestamp createdAt;
}
