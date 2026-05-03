DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS flowers CASCADE;

CREATE TABLE flowers (
    flowers_id SERIAL PRIMARY KEY,
    flowers_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    users_id SERIAL PRIMARY KEY,
    users_name VARCHAR(255) NOT NULL,
    users_favourite_flower_id INT,
    CONSTRAINT fk_favourite_flower 
        FOREIGN KEY(users_favourite_flower_id) 
        REFERENCES flowers(flowers_id)
);

CREATE TABLE garden (
    user_id INT NOT NULL,
    flower_id INT NOT NULL,
    PRIMARY KEY (user_id, flower_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(users_id),
    CONSTRAINT fk_flower FOREIGN KEY (flower_id) REFERENCES flowers(flowers_id)
);

INSERT INTO flowers (flowers_name) VALUES 
    ('Bratek'),
    ('Stokrotka'),
    ('Pierwiosnek'),
    ('Roza'),
    ('Lilia');