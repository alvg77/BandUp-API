package com.bandup.api.specification;

import com.bandup.api.entity.Advertisement;
import com.bandup.api.entity.Genre;
import com.bandup.api.entity.Location;
import com.bandup.api.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class AdvertisementSpecification {
    private AdvertisementSpecification() {}

    public static Specification<Advertisement> hasCityEqual(String city) {
        return (root, query, criteriaBuilder) -> {
            Path<User> user = root.get("user");
            Path<Location> location = user.get("location");
            return criteriaBuilder.equal(location.get("city"), city);
        };
    }

    public static Specification<Advertisement> hasCountryEqual(String country) {
        return (root, query, criteriaBuilder) -> {
            Path<User> user = root.get("user");
            Path<Location> location = user.get("location");
            return criteriaBuilder.equal(location.get("country"), country);
        };
    }

    public static Specification<Advertisement> hasAdministrativeAreaEqual(String administrativeArea) {
        return (root, query, criteriaBuilder) -> {
            Path<User> user = root.get("user");
            Path<Location> location = user.get("location");
            return criteriaBuilder.equal(location.get("administrativeArea"), administrativeArea);
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
            Join<Advertisement, Genre> genres = root.join("searchedArtistTypes");
            return genres.get("id").in(artistTypeIds);
        };
    }

    public static Specification<Advertisement> orderByCreatedAtDesc() {
        return (root, query, criteriaBuilder) -> query.orderBy(criteriaBuilder.desc(root.get("createdAt"))).getRestriction();
    }
}
