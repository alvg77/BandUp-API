package com.bandup.api.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserLocationDTO {
    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private String postalCode;
}
