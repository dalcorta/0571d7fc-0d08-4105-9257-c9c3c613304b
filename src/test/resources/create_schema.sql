CREATE TABLE
    brand (
        id BIGINT NOT NULL,
        description VARCHAR(64) NOT NULL,
        PRIMARY KEY(id)
    );

CREATE TABLE
    product (
        id BIGINT NOT NULL,
        description VARCHAR(64) NOT NULL,
        PRIMARY KEY (id)
    );

CREATE TABLE
    price (
        id BIGINT NOT NULL,
        brand_id BIGINT NOT NULL,
        start_date TIMESTAMP NOT NULL,
        end_date TIMESTAMP NOT NULL,
        product_id BIGINT NOT NULL,
        priority SMALLINT NOT NULL,
        price DECIMAL NOT NULL,
        currency CHAR(3) NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (brand_id) REFERENCES brand(id),
        FOREIGN KEY (product_id) REFERENCES product(id)
    );