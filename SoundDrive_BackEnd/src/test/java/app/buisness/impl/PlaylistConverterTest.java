package app.buisness.impl;

import app.domain.Playlist;
import app.domain.Song;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlaylistConverterTest {
    @Test
    public void testConvert_withNullInput_returnsNull() {
        PlaylistEntity playlist = null;
        Playlist result = PlaylistConverter.convert(playlist);
        assertNull(result);
    }

    @Test
    public void testConvert_withValidInput_returnsExpected() {
        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .ID(1L)
                .name("playlist1")
                .description("playlist description")
                .playlistOwnerId(2L)
                .songs(createSong())
                .build();
        Playlist expected = Playlist.builder()
                .ID(1L)
                .name("playlist1")
                .description("playlist description")
                .playlist_owner_id(2L)
                .songs(createSongs())
                .build();

        Playlist result = PlaylistConverter.convert(playlistEntity);
        assertEquals(expected, result);
    }

    private List<Song> createSongs(){
        List<Song> songs = new ArrayList<>();
        Song song = Song.builder()
                .name("song1")
                .artist("artist1")
                .album_name("album1")
                .year_released("2020")
                .id(1L)
                .build();
        songs.add(song);
        return songs;
    }

    private List<SongEntity> createSong(){
        List<SongEntity> songs = new ArrayList<>();
        SongEntity song = SongEntity.builder()
                .name("song1")
                .artist("artist1")
                .album_name("album1")
                .year_released("2020")
                .id(1L)
                .build();
        songs.add(song);
        return songs;
    }
}
