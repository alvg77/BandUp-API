package com.bandup.api.dto;

import com.bandup.api.util.enumerations.PostFlairEnum;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class PostFlairDTO {
    private Long id;
    private PostFlairEnum name;
}
