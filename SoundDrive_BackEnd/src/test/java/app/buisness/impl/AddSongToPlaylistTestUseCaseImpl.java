package app.buisness.impl;

import app.buisness.exception.InvalidPlaylistException;
import app.buisness.exception.InvalidSongException;
import app.domain.Request.AddSongToPlaylistRequest;
import app.domain.Request.RemoveSongFromPlaylistRequest;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import app.persistence.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddSongToPlaylistTestUseCaseImpl {

    @Mock
    private SongRepository songRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @InjectMocks
    private AddSongToPlaylistUseCaseImpl addSongToPlaylistUseCase;

    @Test
    void shouldFailSongEmpty(){
        Optional<SongEntity> songOptional = Optional.empty();
        Optional<PlaylistEntity> playlistOptional = Optional.ofNullable(PlaylistEntity.builder()
                .ID(1L)
                .name("test")
                .description("testDescription")
                .songs(null)
                .playlistOwnerId(1L)
                .build());

        when(playlistRepository.findById(1L)).thenReturn(Optional.empty());
        when(songRepository.findById(1L)).thenReturn(songOptional);

        AddSongToPlaylistRequest request = AddSongToPlaylistRequest.builder().songId(1L).playlistId(1L).build();

        assertThrows(InvalidSongException.class, () -> addSongToPlaylistUseCase.AddSongToPlaylist(request));
        verify(songRepository).findById(1L);
    }
}
