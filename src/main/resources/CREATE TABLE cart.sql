CREATE TABLE public.cart
(
    good_id INT NOT NULL,
    amount INT DEFAULT 1 NOT NULL,
    reserve_time TIMESTAMP DEFAULT current_timestamp NOT NULL,
    login VARCHAR(32) PRIMARY KEY NOT NULL,
    CONSTRAINT cart_fk_goods_id FOREIGN KEY (good_id) REFERENCES goods (id),
    CONSTRAINT cart_fk_login FOREIGN KEY (login) REFERENCES users (login)
);
