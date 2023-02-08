package app.buisness.impl;

import app.domain.Request.CheckIfUserHasPlaylistRequest;
import app.domain.Response.CheckIfUserHasPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckIfUserHasPlaylistTestUseCaseImpl {
    @Mock
    PlaylistRepository playlistRepository;

    @InjectMocks
    CheckIfUserHasPlaylistUseCaseImpl checkIfUserHasPlaylistUseCase;

    @Test
    void shouldReturnTrue(){

        PlaylistEntity playlistEntity = PlaylistEntity.builder().ID(1L).name("test").description("description").songs(generateSongs()).playlistOwnerId(1L).build();

        List<PlaylistEntity> playlistEntities = new ArrayList<>();
        playlistEntities.add(playlistEntity);

        when(playlistRepository.findByPlaylistOwnerId(1L)).thenReturn(playlistEntities);

        CheckIfUserHasPlaylistResponse.builder().playlistId(1L).build();

        CheckIfUserHasPlaylistRequest request = CheckIfUserHasPlaylistRequest.builder().userId(1L).build();

        //Act
        CheckIfUserHasPlaylistResponse actual = checkIfUserHasPlaylistUseCase.checkIfUserHasPlaylist(request);

        //Assert
        assertEquals(actual.getPlaylistId(), playlistEntity.getID());
    }

    @Test
    void shouldReturnFalse(){

        List<PlaylistEntity> playlistEntities = new ArrayList<>();

        when(playlistRepository.findByPlaylistOwnerId(1L)).thenReturn(playlistEntities);

        CheckIfUserHasPlaylistResponse.builder().playlistId(1L).build();

        CheckIfUserHasPlaylistRequest request = CheckIfUserHasPlaylistRequest.builder().userId(1L).build();

        //Act
        CheckIfUserHasPlaylistResponse actual = checkIfUserHasPlaylistUseCase.checkIfUserHasPlaylist(request);

        //Assert
        assertEquals(0, actual.getPlaylistId());
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
