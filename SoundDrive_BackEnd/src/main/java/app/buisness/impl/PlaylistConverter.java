package app.buisness.impl;

import app.domain.Playlist;
import app.domain.Song;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;

import java.util.*;

public final class PlaylistConverter {
    public static Playlist convert(PlaylistEntity playlist) {
        if (playlist == null) {
            return null;
        }

        if (playlist.getSongs() == null) {
            return Playlist.builder()
                    .ID(playlist.getID())
                    .name(playlist.getName())
                    .description(playlist.getDescription())
                    .playlist_owner_id(playlist.getPlaylistOwnerId())
                    .songs(new ArrayList<>())
                    .build();
        }
        List<Song> songs = new ArrayList<>();
        for (SongEntity entity : playlist.getSongs()) {
            if (entity != null) {
                Song song = Song.builder()
                        .name(entity.getName())
                        .artist(entity.getArtist())
                        .album_name(entity.getAlbum_name())
                        .year_released(entity.getYear_released())
                        .id(entity.getId())
                        .build();
                songs.add(song);
            }
        }
        return Playlist.builder()
                .ID(playlist.getID())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .playlist_owner_id(playlist.getPlaylistOwnerId())
                .songs(songs)
                .build();
    }

}
