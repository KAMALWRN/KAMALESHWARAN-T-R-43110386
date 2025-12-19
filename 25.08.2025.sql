CREATE DATABASE ShopMart;
SHOW DATABASES;
USE ShopMart;
CREATE TABLE category (
    c_id INT PRIMARY KEY,
    c_name VARCHAR(50) NOT NULL,
    c_description VARCHAR(100) NOT NULL
);
CREATE TABLE product (
    p_id INT PRIMARY KEY,
    p_name VARCHAR(50) NOT NULL,
    c_id INT,
    FOREIGN KEY (c_id) REFERENCES category(c_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
CREATE TABLE price (
    price_id INT PRIMARY KEY,
    p_id INT,
    price DECIMAL(10,2),
    FOREIGN KEY (p_id) REFERENCES product(p_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
SHOW TABLES;
INSERT INTO category VALUES 
(201, 'Books', 'Educational and Fictional'),
(202, 'Toys', 'Kids and Collectibles'),
(203, 'Appliances', 'Home and Kitchen devices'),
(204, 'Sports', 'Outdoor and Indoor Equipment');
INSERT INTO product VALUES
(301, 'Novel', 201),
(302, 'Textbook', 201),
(303, 'Action Figure', 202),
(304, 'Puzzle Set', 202),
(305, 'Refrigerator', 203),
(306, 'Washing Machine', 203),
(307, 'Cricket Bat', 204),
(308, 'Football', 204),
(309, 'Tennis Racket', 204);
INSERT INTO price VALUES
(401, 301, 450.00),
(402, 302, 1200.00),
(403, 303, 950.00),
(404, 304, 500.00),
(405, 305, 30000.00),
(406, 306, 25000.00),
(407, 307, 1500.00),
(408, 308, 1200.00),
(409, 309, 3500.00);
SELECT * FROM category;
SELECT * FROM product;
SELECT * FROM price;
DELETE FROM product WHERE p_id=304;
UPDATE product 
SET p_name = 'E-Book Reader', c_id = 201 
WHERE p_id = 301;
SELECT * FROM product WHERE p_name LIKE 'c%';
SELECT * FROM product WHERE p_name LIKE '%r';
SELECT * FROM product WHERE p_name LIKE '%book%';
SELECT * FROM product WHERE p_name LIKE '_a%';
SELECT p_name AS name, c_id AS category
FROM product;
SELECT c_id AS id, c_name AS name 
FROM category;
CREATE VIEW product_view AS
SELECT p_name, c_id
FROM product;
SELECT * FROM product_view;
CREATE OR REPLACE VIEW category_view AS
SELECT c_id, c_name
FROM category;
SELECT * FROM category_view;
DROP VIEW product_view;
