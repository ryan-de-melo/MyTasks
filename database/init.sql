-- POSTGRESQL Initialization

CREATE TABLE users (
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    email             VARCHAR(255) NOT NULL UNIQUE,
    encoded_password  VARCHAR(255) NOT NULL
);