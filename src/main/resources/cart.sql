CREATE TABLE cart
(
  cart_id SERIAL PRIMARY KEY NOT NULL,
  login VARCHAR(256) NOT NULL,
  good_name VARCHAR(256) NOT NULL,
  amount INTEGER DEFAULT 1 NOT NULL,
  reserve_time TIMESTAMP DEFAULT current_timestamp NOT NULL,
  CONSTRAINT cart___fk_user_login FOREIGN KEY (login) REFERENCES users (login),
  CONSTRAINT cart___fk_goods_name FOREIGN KEY (good_name) REFERENCES goods (name)
);
CREATE UNIQUE INDEX cart_cart_id_uindex ON cart (cart_id);
CREATE UNIQUE INDEX cart_login_uindex ON cart (login);
CREATE UNIQUE INDEX cart_good_name_uindex ON cart (good_name);

