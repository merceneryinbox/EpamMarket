CREATE TABLE goods
(
    id SERIAL PRIMARY KEY NOT NULL,
    product_name VARCHAR(256) NOT NULL,
    price REAL NOT NULL,
    amount INT NOT NULL,
    description VARCHAR(1024) NOT NULL,
    image VARCHAR(256)
);
CREATE UNIQUE INDEX goods_id_uindex ON goods (id);