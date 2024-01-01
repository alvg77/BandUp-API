package com.bandup.api.specification;

import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.PostFlair;
import com.bandup.api.entity.User;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class CommunityPostSpecification {
    private CommunityPostSpecification() { }

    public static Specification<CommunityPost> search(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + search + "%");
    }

    public static Specification<CommunityPost> hasFlairIdEqual(Long flairId) {
        return (root, query, criteriaBuilder) -> {
            Path<PostFlair> postFlair = root.get("flair");
            return criteriaBuilder.equal(postFlair.get("id"), flairId);
        };
    }

    public static Specification<CommunityPost> hasUserIdEqual(Long userId) {
        return (root, query, criteriaBuilder) -> {
            Path<User> user = root.join("user");
            return criteriaBuilder.equal(user.get("id"), userId);
        };
    }
}
