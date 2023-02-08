package app.buisness.impl;

import app.buisness.impl.DeletePlaylistUseCaseImpl;
import app.domain.Request.DeletePlaylistRequest;
import app.domain.Response.DeletePlaylistResponse;
import app.persistence.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletePlaylistTestUseCaseImpl {
    @Mock
    private PlaylistRepository playlistRepositoryMock;

    @InjectMocks
    private DeletePlaylistUseCaseImpl deletePlaylistUseCase;

    @Test
    public void deletePlaylist_validPlaylistId_shouldDeletePlaylist() {
        // Arrange
        Long playlistId = 1L;
        DeletePlaylistRequest request = new DeletePlaylistRequest(playlistId);
        doNothing().when(playlistRepositoryMock).deleteById(playlistId);
        // Act
        DeletePlaylistResponse response = deletePlaylistUseCase.deletePlaylist(request);

        // Assert
        assertNotNull(response);
        assertEquals("Successfully Deleted", response.getMessage());
        verify(playlistRepositoryMock, times(1)).deleteById(playlistId);
    }
}
