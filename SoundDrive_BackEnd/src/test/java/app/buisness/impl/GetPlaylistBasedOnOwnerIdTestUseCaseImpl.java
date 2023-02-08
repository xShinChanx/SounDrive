package app.buisness.impl;

import app.buisness.impl.GetPlaylistBasedOnOwnerIdUseCaseImpl;
import app.buisness.impl.PlaylistConverter;
import app.domain.Playlist;
import app.domain.Request.GetPlaylistBasedOnOwnerIdRequest;
import app.domain.Response.GetPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPlaylistBasedOnOwnerIdTestUseCaseImpl {
    @Mock
    private PlaylistRepository playlistRepositoryMock;

    @InjectMocks
    private GetPlaylistBasedOnOwnerIdUseCaseImpl getPlaylistBasedOnOwnerIdUseCase;

    @Test
    public void getPlaylistTest() {
        // Arrange
        Long playlistOwnerId = 1L;
        GetPlaylistBasedOnOwnerIdRequest request = new GetPlaylistBasedOnOwnerIdRequest(playlistOwnerId);

        PlaylistEntity playlist1 = PlaylistEntity.builder().ID(1L).name("playlist1").description("description1").playlistOwnerId(playlistOwnerId).build();
        PlaylistEntity playlist2 = PlaylistEntity.builder().ID(2L).name("playlist2").description("description2").playlistOwnerId(playlistOwnerId).build();
        List<PlaylistEntity> playlists = Arrays.asList(playlist1, playlist2);

        when(playlistRepositoryMock.findByPlaylistOwnerId(playlistOwnerId)).thenReturn(playlists);
        List<Playlist> expectedPlaylists = playlists.stream().map(PlaylistConverter::convert).collect(Collectors.toList());
        GetPlaylistResponse expectedResponse = GetPlaylistResponse.builder().playlists(expectedPlaylists).build();

        // Act
        GetPlaylistResponse actualResponse = getPlaylistBasedOnOwnerIdUseCase.getPlaylist(request);

        // Assert
        assertEquals(expectedResponse.getPlaylists().size(), actualResponse.getPlaylists().size());
        assertEquals(expectedResponse.getPlaylists(), actualResponse.getPlaylists());
    }
}
