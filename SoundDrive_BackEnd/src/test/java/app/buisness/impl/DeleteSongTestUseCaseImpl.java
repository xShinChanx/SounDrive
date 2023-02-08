package app.buisness.impl;

import app.buisness.impl.DeleteSongUseCaseImpl;
import app.domain.Request.DeleteSongRequest;
import app.persistence.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteSongTestUseCaseImpl {
    @Mock
    private SongRepository songRepositoryMock;
    @InjectMocks
    private DeleteSongUseCaseImpl deleteSongUseCase;

    @Test
    public void deleteSong_validInput_shouldDeleteTheSong() {
        // Arrange
        Long songId = 1L;
        DeleteSongRequest request = new DeleteSongRequest(songId);

        // Act
        deleteSongUseCase.deleteSong(request);

        // Assert
        verify(songRepositoryMock, times(1)).deleteById(songId);
    }
}
