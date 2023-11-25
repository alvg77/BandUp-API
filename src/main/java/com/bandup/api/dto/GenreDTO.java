package com.bandup.api.dto;

import com.bandup.api.util.enumerations.GenreEnum;
import lombok.Data;

@Data
public class GenreDTO {
    private Long id;
    private GenreEnum name;
}
