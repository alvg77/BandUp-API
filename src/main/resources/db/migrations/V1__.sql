CREATE TABLE advertisement
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    view_count  BIGINT,
    created_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    user_id     BIGINT,
    CONSTRAINT pk_advertisement PRIMARY KEY (id)
);

CREATE TABLE advertisement_genre
(
    advertisement_id BIGINT NOT NULL,
    genre_id         BIGINT NOT NULL,
    CONSTRAINT pk_advertisement_genre PRIMARY KEY (advertisement_id, genre_id)
);

CREATE TABLE advertisement_instrument
(
    advertisement_id BIGINT NOT NULL,
    instrument_id    BIGINT NOT NULL,
    CONSTRAINT pk_advertisement_instrument PRIMARY KEY (advertisement_id, instrument_id)
);

CREATE TABLE artist_type
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_artist_type PRIMARY KEY (id)
);

CREATE TABLE comment
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    content           VARCHAR(255),
    created_at        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    community_post_id BIGINT                                  NOT NULL,
    user_id           BIGINT                                  NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE community_post
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title         VARCHAR(255),
    content       VARCHAR(255),
    url           VARCHAR(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    post_flair_id BIGINT                                  NOT NULL,
    user_id       BIGINT                                  NOT NULL,
    CONSTRAINT pk_community_post PRIMARY KEY (id)
);

CREATE TABLE contacts
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    phone_number  VARCHAR(255),
    contact_email VARCHAR(255),
    website       VARCHAR(255),
    CONSTRAINT pk_contacts PRIMARY KEY (id)
);

CREATE TABLE genre
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_genre PRIMARY KEY (id)
);

CREATE TABLE "like"
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    post_id BIGINT,
    user_id BIGINT,
    CONSTRAINT pk_like PRIMARY KEY (id)
);

CREATE TABLE post_flair
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_post_flair PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username         VARCHAR(50),
    email            VARCHAR(255),
    password         VARCHAR(255),
    bio              VARCHAR(255),
    profile_picture  VARCHAR(255),
    profile_banner   VARCHAR(255),
    created_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    user_contacts_id BIGINT                                  NOT NULL,
    user_location_id BIGINT                                  NOT NULL,
    artist_type_id   BIGINT                                  NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_genre
(
    genre_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    CONSTRAINT pk_user_genre PRIMARY KEY (genre_id, user_id)
);

CREATE TABLE user_location
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    country     VARCHAR(255),
    city        VARCHAR(255),
    postal_code VARCHAR(255),
    CONSTRAINT pk_user_location PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT user_email_unique UNIQUE (email);

ALTER TABLE "user"
    ADD CONSTRAINT user_username_unique UNIQUE (username);

CREATE INDEX user_email_index ON "user" (email);

CREATE INDEX user_username_index ON "user" (username);

ALTER TABLE advertisement
    ADD CONSTRAINT FK_ADVERTISEMENT_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_COMMUNITYPOST FOREIGN KEY (community_post_id) REFERENCES community_post (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE community_post
    ADD CONSTRAINT FK_COMMUNITY_POST_ON_POST_FLAIR FOREIGN KEY (post_flair_id) REFERENCES post_flair (id);

ALTER TABLE community_post
    ADD CONSTRAINT FK_COMMUNITY_POST_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE "like"
    ADD CONSTRAINT FK_LIKE_ON_POST FOREIGN KEY (post_id) REFERENCES community_post (id);

ALTER TABLE "like"
    ADD CONSTRAINT FK_LIKE_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_ARTIST_TYPE FOREIGN KEY (artist_type_id) REFERENCES artist_type (id);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_USER_CONTACTS FOREIGN KEY (user_contacts_id) REFERENCES contacts (id);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_USER_LOCATION FOREIGN KEY (user_location_id) REFERENCES user_location (id);

ALTER TABLE advertisement_genre
    ADD CONSTRAINT fk_advgen_on_advertisement FOREIGN KEY (advertisement_id) REFERENCES advertisement (id);

ALTER TABLE advertisement_genre
    ADD CONSTRAINT fk_advgen_on_genre FOREIGN KEY (genre_id) REFERENCES genre (id);

ALTER TABLE advertisement_instrument
    ADD CONSTRAINT fk_advins_on_advertisement FOREIGN KEY (advertisement_id) REFERENCES advertisement (id);

ALTER TABLE advertisement_instrument
    ADD CONSTRAINT fk_advins_on_artist_type FOREIGN KEY (instrument_id) REFERENCES artist_type (id);

ALTER TABLE user_genre
    ADD CONSTRAINT fk_usegen_on_genre FOREIGN KEY (genre_id) REFERENCES genre (id);

ALTER TABLE user_genre
    ADD CONSTRAINT fk_usegen_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);