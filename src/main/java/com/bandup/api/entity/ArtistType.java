package com.bandup.api.entity;

import com.bandup.api.util.enumerations.ArtistTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`artist_type`")
public class ArtistType {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private ArtistTypeEnum name;
}
