DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS flowers CASCADE;

CREATE TABLE flowers (
    flowers_id INT PRIMARY KEY,
    flowers_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    users_id INT PRIMARY KEY,
    users_name VARCHAR(255) NOT NULL,
    users_favourite_flower_id INT,
    CONSTRAINT fk_favourite_flower 
        FOREIGN KEY(users_favourite_flower_id) 
        REFERENCES flowers(flowers_id)
);

INSERT INTO flowers (flowers_id, flowers_name) VALUES 
    (1, 'Bratek'),
    (2, 'Stokrotka'),
    (3, 'Pierwiosnek'),
    (4, 'Roza'),
    (5, 'Lilia');