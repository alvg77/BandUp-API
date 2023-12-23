package com.bandup.api.entity;

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
    private String name;
}
