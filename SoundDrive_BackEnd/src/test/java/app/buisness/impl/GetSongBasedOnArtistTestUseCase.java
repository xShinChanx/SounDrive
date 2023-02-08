package app.buisness.impl;

import app.buisness.exception.InvalidSongException;
import app.domain.Request.GetSongBasedOnArtistRequest;
import app.domain.Response.GetSongBasedOnArtistResponse;
import app.persistence.Entity.SongEntity;
import app.persistence.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetSongBasedOnArtistTestUseCase {

    @Mock
    SongRepository songRepository;

    @InjectMocks
    GetSongBasedOnArtistUseCaseImpl getSongsBasedOnArtistUseCase;

    @Test
    public void getSongs_ShouldReturnSongs_WhenArtistIsValid() {
        // Arrange
        String artist = "James Blunt";
        GetSongBasedOnArtistRequest request = new GetSongBasedOnArtistRequest(artist);
        List<SongEntity> songEntities = Arrays.asList(
                new SongEntity(1L, "You're Beautiful", artist, "Back to Bedlam", "2004", null),
                new SongEntity(2L, "Goodbye My Lover", artist, "Back to Bedlam", "2004", null)
        );
        when(songRepository.findAllSongsByArtist(artist)).thenReturn(songEntities);

        // Act
        GetSongBasedOnArtistResponse response = getSongsBasedOnArtistUseCase.getSongs(request);

        // Assert
        assertEquals(2, response.getListOfSongs().size());
        assertEquals("James Blunt", response.getListOfSongs().get(0).getArtist());
    }

    @Test
    public void getSongs_ShouldThrowInvalidSongException_WhenArtistIsInvalid() {
        // Arrange
        String artist = "InvalidArtist";
        GetSongBasedOnArtistRequest request = new GetSongBasedOnArtistRequest(artist);
        when(songRepository.findAllSongsByArtist(artist)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(InvalidSongException.class, () -> getSongsBasedOnArtistUseCase.getSongs(request));
    }
}
