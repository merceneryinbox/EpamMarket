CREATE TABLE users
(
  login    VARCHAR(32)               NOT NULL,
  email    VARCHAR(256)              NOT NULL,
  phone    VARCHAR(32)               NOT NULL,
  password VARCHAR(128)              NOT NULL,
  status   VARCHAR(32) DEFAULT FALSE NOT NULL,
  id_user  SERIAL                    NOT NULL
    CONSTRAINT users_id_user_pk
    PRIMARY KEY
);

CREATE UNIQUE INDEX users_login_uindex
  ON users (login);

CREATE UNIQUE INDEX users_email_uindex
  ON users (email);

CREATE UNIQUE INDEX users_phone_number_uindex
  ON users (phone);

CREATE UNIQUE INDEX users_id_user_uindex
  ON users (id_user);

