CREATE TABLE movie
(
    id            INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(30) NOT NULL,
    director_name VARCHAR(30) NOT NULL,
    genre         VARCHAR(20) NOT NULL
)