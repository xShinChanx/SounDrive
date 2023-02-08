package app.buisness.impl;

import app.buisness.exception.InvalidPlaylistException;
import app.buisness.exception.InvalidSongException;
import app.buisness.exception.InvalidUserException;
import app.buisness.impl.RemoveSongFromPlaylistUseCaseImpl;
import app.domain.Request.CreateUserRequest;
import app.domain.Request.GetSongBasedOnIdRequest;
import app.domain.Request.RemoveSongFromPlaylistRequest;
import app.domain.Response.RemoveSongFromPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.PlaylistSongEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import app.persistence.PlaylistSongRepository;
import app.persistence.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveSongFromPlaylistTestUseCaseImpl {
    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private PlaylistSongRepository playlistSongRepository;

    @InjectMocks
    private RemoveSongFromPlaylistUseCaseImpl removeSongFromPlaylistUseCaseImpl;

    @Test
    public void removeSongFromPlaylist() {
        // mock the request
        RemoveSongFromPlaylistRequest request = RemoveSongFromPlaylistRequest.builder()
                .playlistId(1L)
                .songId(2L)
                .build();

        // mock the playlist entity
        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .ID(1L)
                .name("test")
                .description("testDescription")
                .songs(null)
                .playlistOwnerId(1L)
                .build();

        playlistEntity.setSongs(generateSongs());

        // mock the song entity
        SongEntity songEntity = SongEntity.builder()
                .id(2L)
                .name("songName")
                .artist("testArtist")
                .album_name("testAlbum")
                .year_released("2020jan")
                .build();

        // mock the playlist song entity
        PlaylistSongEntity playlistSongEntity = PlaylistSongEntity.builder()
                .playlist(playlistEntity)
                .song(songEntity)
                .build();

        // mock the repository calls
        when(playlistRepository.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(songRepository.findById(any())).thenReturn(Optional.of(songEntity));
        when(playlistSongRepository.findByPlaylistAndSong(any(), any())).thenReturn(playlistSongEntity);

        // call the method under test
        RemoveSongFromPlaylistResponse response = removeSongFromPlaylistUseCaseImpl.AddSongToPlaylist(request);

        // assert the response
        assertEquals(1, response.getPlaylistId());
    }

    @Test
    void removeSongFailPlaylistEmpty(){
        Optional<PlaylistEntity> playlistOptional = Optional.empty();

        when(playlistRepository.findById(1L)).thenReturn(playlistOptional);

        RemoveSongFromPlaylistRequest request = RemoveSongFromPlaylistRequest.builder().songId(1L).playlistId(1L).build();

        assertThrows(InvalidPlaylistException.class, () -> removeSongFromPlaylistUseCaseImpl.AddSongToPlaylist(request));
        verify(playlistRepository).findById(1L);
    }

    @Test
    void removeSongFailSongEmpty(){
        //Arrange
        Optional<SongEntity> songOptional = Optional.empty();
        Optional<PlaylistEntity> playlistOptional = Optional.ofNullable(PlaylistEntity.builder()
                .ID(1L)
                .name("test")
                .description("testDescription")
                .songs(null)
                .playlistOwnerId(1L)
                .build());

        when(playlistRepository.findById(1L)).thenReturn(playlistOptional);
        when(songRepository.findById(1L)).thenReturn(songOptional);

        RemoveSongFromPlaylistRequest request = RemoveSongFromPlaylistRequest.builder().songId(1L).playlistId(1L).build();
        assertThrows(InvalidSongException.class, () -> removeSongFromPlaylistUseCaseImpl.AddSongToPlaylist(request));
        verify(songRepository).findById(1L);
    }


    private List<SongEntity> generateSongs(){
        SongEntity song = SongEntity.builder()
                .id(2L)
                .name("songName")
                .artist("testArtist")
                .album_name("testAlbum")
                .year_released("2020jan").build();

        List<SongEntity> songEntities = new ArrayList<>();
        songEntities.add(song);
        return  songEntities;
    }
}
