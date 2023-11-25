package com.bandup.api.repository;

import com.bandup.api.entity.PostFlair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFlairRepository extends JpaRepository<PostFlair, Long> { }
