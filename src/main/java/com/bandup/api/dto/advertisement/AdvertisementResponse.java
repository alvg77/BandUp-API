package com.bandup.api.dto.advertisement;

import com.bandup.api.dto.ArtistTypeDTO;
import com.bandup.api.dto.GenreDTO;
import com.bandup.api.dto.LocationDTO;
import com.bandup.api.dto.user.UserDetailResponse;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
public class AdvertisementResponse {
    private Long id;
    private String title;
    private String description;
    private Long viewCount;
    private LocationDTO location;
    private Set<GenreDTO> genres;
    private Set<ArtistTypeDTO> searched;
    private UserDetailResponse creator;
    private Timestamp createdAt;
}
