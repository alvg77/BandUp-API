package com.bandup.api.entity;

import com.bandup.api.util.enumerations.GenreEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`genre`")
public class Genre {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private GenreEnum name;
}
