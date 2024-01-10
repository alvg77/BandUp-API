package com.bandup.api.entity;

// community posting - questions answers etc

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`community_post`")
public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "`url`", nullable = true)
    private String url;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_flair_id", referencedColumnName = "id")
    private PostFlair flair;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.REMOVE)
    private List<Comment> comments = List.of();

    @ManyToMany(mappedBy = "likedPosts")
    private Set<User> likedByUsers = new HashSet<>();

    @ManyToMany(mappedBy = "dislikedPosts")
    private Set<User> dislikedByUsers = new HashSet<>();

    @ManyToOne(optional = false)
    private User user;
}
