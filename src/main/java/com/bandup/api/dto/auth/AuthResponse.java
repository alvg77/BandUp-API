package com.bandup.api.dto.auth;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.dto.GenreDTO;
import com.bandup.api.dto.UserLocationDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
public class AuthResponse {
    private String token;
}
