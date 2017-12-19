CREATE TABLE cart
(
  cart_id SERIAL PRIMARY KEY NOT NULL,
  user_id INTEGER NOT NULL,
  goods_id INTEGER NOT NULL,
  amount INTEGER DEFAULT 1 NOT NULL,
  reserve_time TIMESTAMP DEFAULT current_timestamp NOT NULL,
  CONSTRAINT cart___fk__users FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT cart___fk__goods FOREIGN KEY (goods_id) REFERENCES goods (goods_id)
);

CREATE UNIQUE INDEX cart_cart_id_uindex ON cart (cart_id);

