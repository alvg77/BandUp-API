package com.bandup.api.repository;

import com.bandup.api.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Set<Genre> getGenresByIdIsIn(Set<Long> ids);
}
