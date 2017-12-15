CREATE TABLE goods
(
  id_good      SERIAL        NOT NULL
    CONSTRAINT goods_pkey
    PRIMARY KEY,
  product_name VARCHAR(256)  NOT NULL,
  price        REAL          NOT NULL,
  amount       INTEGER       NOT NULL,
  description  VARCHAR(1024) NOT NULL,
  image        VARCHAR(256)
);

CREATE UNIQUE INDEX goods_id_uindex
  ON goods (id_good);

