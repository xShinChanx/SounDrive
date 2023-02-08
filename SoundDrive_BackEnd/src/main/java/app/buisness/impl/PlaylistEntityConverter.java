package app.buisness.impl;

import app.buisness.exception.InvalidPlaylistException;
import app.domain.Playlist;
import app.domain.Song;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

final class PlaylistEntityConverter {

    public static PlaylistEntity convert(Optional<PlaylistEntity> playlist){
        if(playlist.isEmpty()){
            throw new InvalidPlaylistException("PLAYLIST DOESNT EXIST");
        }
        List<SongEntity> songs = new ArrayList<SongEntity>();
        for(SongEntity entity : playlist.get().getSongs()){
            SongEntity song = SongEntity.builder()
                    .name(entity.getName())
                    .artist(entity.getArtist())
                    .album_name(entity.getAlbum_name())
                    .year_released(entity.getYear_released())
                    .id(entity.getId())
                    .build();
            songs.add(song);
        }

        return PlaylistEntity.builder()
                .ID(playlist.get().getID())
                .name(playlist.get().getName())
                .description(playlist.get().getDescription())
                .playlistOwnerId(playlist.get().getPlaylistOwnerId())
                .songs(songs)
                .build();
    }
}
