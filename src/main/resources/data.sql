DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS flowers CASCADE;

CREATE TABLE flowers (
    flowers_id INT AUTO_INCREMENT PRIMARY KEY,
    flowers_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    users_id INT AUTO_INCREMENT PRIMARY KEY,
    users_name VARCHAR(255) NOT NULL,
    users_favourite_flower_id INT
);

INSERT INTO flowers (flowers_name) VALUES
    ('Bratek'),
    ('Stokrotka'),
    ('Pierwiosnek'),
    ('Roza'),
    ('Lilia');