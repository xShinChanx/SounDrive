package app.buisness.impl;

import app.buisness.impl.GetPlaylistsUseCaseImpl;
import app.buisness.impl.PlaylistConverter;
import app.domain.Playlist;
import app.domain.Response.GetPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPlaylistsTestUseCaseImpl {
    @Mock
    PlaylistRepository playlistRepositoryMock;

    @InjectMocks
    GetPlaylistsUseCaseImpl getPlaylistsUseCase;

    @Test
    void getPlaylistsTest() {
        // Arrange
        List<PlaylistEntity> playlistEntities = new ArrayList<>();
        playlistEntities.add(PlaylistEntity.builder().ID(1L).name("Playlist 1").description("playlist 1 description").build());
        playlistEntities.add(PlaylistEntity.builder().ID(2L).name("Playlist 2").description("playlist 2 description").build());
        when(playlistRepositoryMock.findAll()).thenReturn(playlistEntities);
        List<Playlist> expectedPlaylists = playlistEntities.stream().map(PlaylistConverter::convert).collect(Collectors.toList());
        GetPlaylistResponse expectedResponse = GetPlaylistResponse.builder().playlists(expectedPlaylists).build();

        // Act
        GetPlaylistResponse actualResponse = getPlaylistsUseCase.getPlaylist();

        // Assert
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getPlaylists());
        assertEquals(expectedResponse.getPlaylists().size(), actualResponse.getPlaylists().size());
        assertEquals(expectedResponse.getPlaylists(), actualResponse.getPlaylists());
    }
}
