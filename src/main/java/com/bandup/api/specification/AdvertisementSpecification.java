package com.bandup.api.specification;

import com.bandup.api.dto.LocationDTO;
import com.bandup.api.entity.Advertisement;
import com.bandup.api.entity.Genre;
import com.bandup.api.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AdvertisementSpecification {
    private AdvertisementSpecification() {}

    public static Specification<Advertisement> search(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + search + "%");
    }

    public static Specification<Advertisement> hasLocation(LocationDTO location) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (location.getPostalCode() != null && !location.getPostalCode().isEmpty()) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("location").get("postalCode"), location.getPostalCode())
                );
            }

            if (location.getCity() != null && !location.getCity().isEmpty()) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("location").get("city"), location.getCity())
                );
            }

            if (location.getCountry() != null && !location.getCountry().isEmpty()) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("location").get("country"), location.getCountry())
                );
            }

            return predicate;
        };
    }

    public static Specification<Advertisement> hasUserIdEqual(Long userId) {
        return (root, query, criteriaBuilder) -> {
            Path<User> user = root.join("user");
            return criteriaBuilder.equal(user.get("id"), userId);
        };
    }

    public static Specification<Advertisement> hasGenreIdsIn(Long[] genreIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Advertisement, Genre> genres = root.join("genres");
            return genres.get("id").in(genreIds);
        };
    }

    public static Specification<Advertisement> hasArtistTypeIdsIn(Long[] artistTypeIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Advertisement, Genre> genres = root.join("searched");
            return genres.get("id").in(artistTypeIds);
        };
    }
}
