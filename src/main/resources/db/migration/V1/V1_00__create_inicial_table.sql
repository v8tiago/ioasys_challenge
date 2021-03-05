CREATE TABLE user
(
    id         INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_name  VARCHAR(30) NOT NULL,
    is_deleted BOOLEAN     NOT NULL,
    is_admin   BOOLEAN
)