package com.bandup.api.entity;

import com.bandup.api.util.enumerations.PostFlairEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`post_flair`")
public class PostFlair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostFlairEnum name;
}
