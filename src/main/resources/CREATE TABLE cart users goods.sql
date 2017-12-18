CREATE TABLE users
(
    login VARCHAR(32) NOT NULL,
    email VARCHAR(256) NOT NULL,
    phone VARCHAR(32) NOT NULL,
    password VARCHAR(128) NOT NULL,
    status VARCHAR(32) DEFAULT false NOT NULL,
    id INTEGER DEFAULT nextval('users_id_user_seq'::regclass) PRIMARY KEY NOT NULL
);
CREATE TABLE cart
(
    id INTEGER DEFAULT nextval('cart_id_seq'::regclass) PRIMARY KEY NOT NULL,
    amount_cart INTEGER DEFAULT 1 NOT NULL,
    reserve_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_good INTEGER NOT NULL,
    CONSTRAINT cart_fk_id_good FOREIGN KEY (id_good) REFERENCES goods (id)
);
CREATE UNIQUE INDEX cart_id_uindex ON cart (id);
CREATE TABLE goods
(
    id INTEGER DEFAULT nextval('goods_id_seq'::regclass) PRIMARY KEY NOT NULL,
    product_name VARCHAR(256) NOT NULL,
    price REAL NOT NULL,
    amount INTEGER NOT NULL,
    description VARCHAR(1024) NOT NULL,
    image VARCHAR(256)
);
CREATE UNIQUE INDEX goods_id_uindex ON goods (id);

CREATE UNIQUE INDEX users_login_uindex ON users (login);
CREATE UNIQUE INDEX users_email_uindex ON users (email);
CREATE UNIQUE INDEX users_phone_number_uindex ON users (phone);
CREATE UNIQUE INDEX users_id_user_uindex ON users (id); 