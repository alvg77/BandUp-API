package com.bandup.api.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "`user`",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_username_unique", columnNames = "username"),
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
        },
        indexes = {
                @Index(name = "user_username_index", columnList = "username"),
                @Index(name = "user_email_index", columnList = "email"),
        }
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String username;
    private String email;
    private String password;
    @Nullable
    private String bio;
    @Nullable
    private String profilePicture;
    @Nullable
    private String profileBanner;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    // --------------------------------- RELATIONSHIPS ---------------------------------
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_location_id`", referencedColumnName = "`id`")
    private UserLocation location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "`artist_type_id`", referencedColumnName = "`id`")
    private ArtistType artistType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommunityPost> communityPosts = List.of();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Advertisement> advertisements = List.of();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = List.of();

    @ManyToMany
    @JoinTable(
            name = "user_genre",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = Set.of();

    @ElementCollection
    @CollectionTable(name = "user_social_media", joinColumns = @JoinColumn(name = "user_id"))
    private Set<String> socialMedia = Set.of();

    // --------------------------------- AUTHENTICATION METHODS ---------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}