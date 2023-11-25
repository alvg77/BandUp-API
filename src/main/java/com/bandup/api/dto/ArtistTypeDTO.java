package com.bandup.api.dto;

import com.bandup.api.util.enumerations.ArtistTypeEnum;
import lombok.Data;

@Data
public class ArtistTypeDTO {
    private Long id;
    private ArtistTypeEnum name;
}
