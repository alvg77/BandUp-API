package com.bandup.api.repository;

import com.bandup.api.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Long countByPostId(Long postId);
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
}
