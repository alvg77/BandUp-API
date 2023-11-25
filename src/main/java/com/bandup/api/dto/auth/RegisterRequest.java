package com.bandup.api.dto.auth;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.dto.GenreDTO;
import com.bandup.api.dto.UserLocationDTO;
import com.bandup.api.entity.Genre;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class RegisterRequest {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String displayName;
    @NonNull
    private Long artistTypeId;
    @NonNull
    private Set<Long> genreIds;
    private String bio;
    @NonNull
    private UserLocationDTO location;
}
