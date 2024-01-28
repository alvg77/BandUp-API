package com.bandup.api.repository;

import com.bandup.api.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, PagingAndSortingRepository<Comment, Long> {
    List<Comment> findAllByCommunityPostIdOrderByCreatedAtDesc(Long postId, Pageable pageable);
}
