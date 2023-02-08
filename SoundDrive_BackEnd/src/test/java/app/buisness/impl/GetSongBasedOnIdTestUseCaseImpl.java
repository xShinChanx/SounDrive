package app.buisness.impl;


import app.buisness.exception.InvalidPlaylistException;
import app.buisness.exception.InvalidSongException;
import app.buisness.exception.InvalidUserException;
import app.buisness.impl.GetSongBasedOnIdUseCaseImpl;
import app.domain.Request.GetSongBasedOnIdRequest;
import app.domain.Response.GetSongBasedOnIdResponse;
import app.persistence.Entity.SongEntity;
import app.persistence.Entity.UserEntity;
import app.persistence.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetSongBasedOnIdTestUseCaseImpl {
    @Mock
    SongRepository songRepositoryMock;

    @InjectMocks
    GetSongBasedOnIdUseCaseImpl getSongBasedOnIdUseCase;

    @Test
    void getSongTest() {
        // Arrange
        Long songId = 1L;
        GetSongBasedOnIdRequest request = new GetSongBasedOnIdRequest(songId);
        SongEntity song = SongEntity.builder().id(songId).name("song 1").artist("artist 1").album_name("album 1").year_released("2021").build();
        Optional<SongEntity> expectedsong = Optional.of(song);
        when(songRepositoryMock.findById(songId)).thenReturn(expectedsong);
        GetSongBasedOnIdResponse expectedResponse = GetSongBasedOnIdResponse.builder().songId(song.getId()).name(song.getName()).album_name(song.getAlbum_name()).artist(song.getArtist()).year_released(song.getYear_released()).build();

        // Act
        GetSongBasedOnIdResponse actualResponse = getSongBasedOnIdUseCase.getSong(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getSongId(), actualResponse.getSongId());
    }

    @Test
    void getSongFail(){
        //Arrange
        Optional<SongEntity> songOptional = Optional.empty();
        when(songRepositoryMock.findById(1L)).thenReturn(songOptional);

        GetSongBasedOnIdRequest request = GetSongBasedOnIdRequest.builder().songId(1L).build();
        assertThrows(InvalidSongException.class, () -> getSongBasedOnIdUseCase.getSong(request));
        verify(songRepositoryMock).findById(1L);
    }
}
