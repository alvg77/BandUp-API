package com.bandup.api.dto.advertisement;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AdvertisementRequest {
    public String title;
    public String description;
    public Set<Long> genreIds;
    public Set<Long> searchedArtistTypeIds;
}
