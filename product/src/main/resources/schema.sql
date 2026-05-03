DROP TABLE IF EXISTS category;
create table category
(
    category_id int auto_increment
        primary key,
    category    varchar(100) not null,
    tag         varchar(100) not null,
    status      tinyint      not null,
    constraint ux_category
        unique (category),
    constraint ux_tag
        unique (tag)
);

DROP TABLE IF EXISTS product;

CREATE TABLE product(
                        product_id INT NOT NULL AUTO_INCREMENT,
                        gtin CHAR(13) NOT NULL,
                        product VARCHAR(100) NOT NULL,
                        description TEXT,
                        price FLOAT NOT NULL,
                        stock INT NOT NULL,
                        category_id INT NOT NULL,
                        status TINYINT NOT NULL,
                        PRIMARY KEY (product_id),
                        CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE UNIQUE INDEX ux_product_gtin ON product(gtin);
CREATE UNIQUE INDEX ux_product_product ON product(product);

DROP TABLE IF EXISTS product_image;

CREATE TABLE product_image(
                              product_image_id INT NOT NULL AUTO_INCREMENT,
                              product_id INT NOT NULL,
                              image VARCHAR(255) NOT NULL,
                              status TINYINT NOT NULL,
                              PRIMARY KEY (product_image_id),
                              CONSTRAINT fk_product_image_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE IF NOT EXISTS user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role     VARCHAR(100) NOT NULL
);