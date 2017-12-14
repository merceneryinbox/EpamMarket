CREATE TABLE public.users
(
    login VARCHAR(32) PRIMARY KEY NOT NULL,
    email VARCHAR(256) NOT NULL,
    phone_number BIGINT NOT NULL,
    password VARCHAR(128) NOT NULL,
    admin_permis BOOLEAN DEFAULT FALSE  NOT NULL,
    blocked BOOLEAN DEFAULT FALSE  NOT NULL
);
CREATE UNIQUE INDEX users_login_uindex ON public.users (login);
CREATE UNIQUE INDEX users_email_uindex ON public.users (email);
CREATE UNIQUE INDEX users_phone_number_uindex ON public.users (phone_number);