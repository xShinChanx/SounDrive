package app.buisness.impl;

import app.buisness.exception.InvalidSongException;
import app.domain.Song;
import app.persistence.Entity.SongEntity;

import java.util.Optional;

final class SongEntityConverter {
    public static SongEntity convert(Optional<SongEntity> song){
        if(song.isEmpty()){
            throw new InvalidSongException("SONG DOESN'T EXIST");
        }
        return SongEntity.builder()
                .name(song.get().getName())
                .artist(song.get().getArtist())
                .album_name(song.get().getAlbum_name())
                .year_released(song.get().getYear_released())
                .id(song.get().getId())
                .build();
    }
}
