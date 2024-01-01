package com.bandup.api.dto.auth;

import com.bandup.api.dto.LocationDTO;
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
    private Long artistTypeId;
    @NonNull
    private Set<Long> genreIds;
    private String bio;
    @NonNull
    private LocationDTO location;
}
