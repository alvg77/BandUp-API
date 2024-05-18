package com.bandup.api.prototype;

import com.bandup.api.entity.CommunityPost;
import com.bandup.api.entity.PostFlair;
import com.bandup.api.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CommunityPostPrototype {
    private static long id = 1;

    public static CommunityPost aCommunityPost(User user) {
        return CommunityPost.builder()
                .id(id++)
                .likes(new ArrayList<>())
                .title("Title")
                .url(null)
                .flair(new PostFlair(1L, "Flair"))
                .content("Content")
                .user(user)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
