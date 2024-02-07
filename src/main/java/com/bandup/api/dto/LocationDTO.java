package com.bandup.api.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LocationDTO {
    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private String administrativeArea;
    @NonNull
    private Double lat;
    @NonNull
    private Double lon;
}
