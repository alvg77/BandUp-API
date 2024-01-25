package com.bandup.api.repository;

import com.bandup.api.entity.ArtistType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArtistTypeRepository extends JpaRepository<ArtistType, Long> {
    Set<ArtistType> getArtistTypesByIdIsIn(Set<Long> ids);
}
