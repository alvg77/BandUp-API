package com.bandup.api.prototype;

import com.bandup.api.entity.*;

import java.util.Set;

public class UserPrototype {
    private static long id = 1;

    public static User aUser() {
        return User.builder()
                .id(id++)
                .username("username")
                .email("email@mail.com")
                .password("password")
                .bio("Bio")
                .artistType(new ArtistType(1L, "GUITARIST"))
                .location(new Location(
                        1L,
                        "Bulgaria",
                        "Sofia",
                        "Sofia-city",
                        186.123123,
                        186.123123
                ))
                .contacts(new Contacts(1L, null, "contact@mail.com", "null"))
                .profilePictureKey(null)
                .genres(Set.of(
                        new Genre(1L, "METAL")
                ))
                .build();
    }
}
