package app.buisness.impl;

import app.buisness.CreatePlaylistUseCase;
import app.buisness.exception.InvalidPlaylistException;
import app.buisness.impl.CreatePlaylistUseCaseImpl;
import app.domain.Request.CreatePlaylistRequest;
import app.domain.Response.CreatePlaylistResponse;
import app.domain.Response.CreateSongResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreatePlaylistTestUseCaseImpl {

    @Mock
    private PlaylistRepository playlistRepository;
    @InjectMocks
    private CreatePlaylistUseCaseImpl createPlaylistUseCase;

    @Test
    void createPlaylist(){
        PlaylistEntity playlist = PlaylistEntity.builder().description("TestDescription").name("TestName").playlistOwnerId(1L).songs(null).build();
        CreatePlaylistRequest request = CreatePlaylistRequest.builder().description("TestDescription").name("TestName").userId(1L).build();

        when(playlistRepository.save(any(PlaylistEntity.class))).thenReturn(playlist);

        CreatePlaylistResponse response = createPlaylistUseCase.createPlaylist(request);

        assertEquals(response.getName(), playlist.getName());
    }

}
