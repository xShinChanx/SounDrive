CREATE TABLE user
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50)  NOT NULL,
    email varchar(50)  NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE user_role
(
    id int NOT NULL AUTO_INCREMENT,
        user_id int NULL,
        role_name varchar(50) NOT NULL,
        PRIMARY KEY (id),
        UNIQUE (user_id, role_name),
        FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
