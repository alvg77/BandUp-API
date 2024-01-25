package com.bandup.api.specification;

import com.bandup.api.entity.Advertisement;
import com.bandup.api.entity.Genre;
import com.bandup.api.entity.Location;
import com.bandup.api.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AdvertisementSpecification {
    private AdvertisementSpecification() {}

    public static Specification<Advertisement> hasPostalCodeEqual(String postalCode, String city, String country) {
        return (root, query, criteriaBuilder) -> {
            Path<Location> location = root.get("location");

            Predicate postalCodePredicate = criteriaBuilder.equal(location.get("postalCode"), postalCode);
            Predicate cityPredicate = criteriaBuilder.equal(location.get("city"), city);
            Predicate countryPredicate = criteriaBuilder.equal(location.get("country"), country);

            return criteriaBuilder.and(postalCodePredicate, cityPredicate, countryPredicate);
        };
    }

    public static Specification<Advertisement> hasCountryEqual(String country) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), country);
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

    public static Specification<Advertisement> orderByCreatedAtDesc() {
        return (root, query, criteriaBuilder) -> {
            return query.orderBy(criteriaBuilder.desc(root.get("createdAt"))).getRestriction();
        };
    }
}
