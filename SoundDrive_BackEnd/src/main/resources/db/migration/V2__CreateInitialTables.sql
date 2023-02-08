CREATE TABLE playlist
(
    id INT AUTO_INCREMENT,
    name varchar(50),
    description varchar(100),
    playlist_owner_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (playlist_owner_id) REFERENCES user(id)
);

CREATE TABLE song
(
    id INT AUTO_INCREMENT,
    name varchar(50),
    artist varchar(50),
    album_name varchar(50),
    year_released varchar(50),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE playlist_song (
    id INT AUTO_INCREMENT PRIMARY KEY,
    playlist_id INT NOT NULL,
    song_id INT NOT NULL,
    FOREIGN KEY (playlist_id) REFERENCES playlist(id) ON DELETE CASCADE,
    FOREIGN KEY (song_id) REFERENCES song(id) ON DELETE CASCADE
);