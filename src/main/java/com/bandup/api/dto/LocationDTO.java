package com.bandup.api.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LocationDTO {
    private Long id;
    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private String postalCode;
}
