package app.buisness.impl;

import app.domain.Song;
import app.persistence.Entity.SongEntity;

public final class SongConverter {
    public static Song convert(SongEntity song){
        return Song.builder()
                .name(song.getName())
                .artist(song.getArtist())
                .album_name(song.getAlbum_name())
                .year_released(song.getYear_released())
                .id(song.getId())
                .build();
    }

}
