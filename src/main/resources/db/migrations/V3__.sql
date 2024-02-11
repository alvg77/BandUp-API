ALTER TABLE "user"
    ADD profile_picture_key VARCHAR(255);

ALTER TABLE "user"
    DROP COLUMN profile_banner;

ALTER TABLE "user"
    DROP COLUMN profile_picture;