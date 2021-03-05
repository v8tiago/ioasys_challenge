CREATE TABLE movie_actors
(
    id    INT(6) UNSIGNED,
    actor VARCHAR(30) NOT NULL,
    CONSTRAINT FK_Movie FOREIGN KEY (id) REFERENCES movie (id)
)