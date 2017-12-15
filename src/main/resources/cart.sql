CREATE TABLE cart
(
  id_cart      SERIAL                              NOT NULL
    CONSTRAINT cart_id_pk
    PRIMARY KEY,
  amount_cart  INTEGER DEFAULT 1                   NOT NULL,
  reserve_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  id_good      INTEGER                             NOT NULL
    CONSTRAINT cart_fk_good_id
    REFERENCES goods,
  id_user      INTEGER                             NOT NULL
);

CREATE UNIQUE INDEX cart_id_uindex
  ON cart (id_cart);

