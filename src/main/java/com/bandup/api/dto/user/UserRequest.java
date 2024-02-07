package com.bandup.api.dto.user;

import com.bandup.api.dto.LocationDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserRequest {
    private String username;
    private String profilePicture;
    private String bio;
    private Set<Long> genreIds;
    private Long artistTypeId;
    private LocationDTO location;
}


