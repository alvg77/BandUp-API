ALTER TABLE user_location
    ADD administrative_area VARCHAR(255);

ALTER TABLE user_location
    ADD lat DOUBLE PRECISION;

ALTER TABLE user_location
    ADD lon DOUBLE PRECISION;

ALTER TABLE user_location
    DROP COLUMN postal_code;
