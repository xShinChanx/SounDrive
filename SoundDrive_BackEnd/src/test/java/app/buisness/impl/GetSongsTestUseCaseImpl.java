package app.buisness.impl;

import app.buisness.impl.GetSongsUseCaseImpl;
import app.buisness.impl.SongConverter;
import app.domain.Response.GetAllSongsResponse;
import app.domain.Song;
import app.persistence.Entity.SongEntity;
import app.persistence.SongRepository;
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
public class GetSongsTestUseCaseImpl {
    @Mock
    SongRepository songRepositoryMock;

    @InjectMocks
    GetSongsUseCaseImpl getSongsUseCase;

    @Test
    void getSongsTest() {
        // Arrange
        List<SongEntity> songEntities = new ArrayList<>();
        songEntities.add(SongEntity.builder().id(1L).name("Song 1").artist("artist 1").album_name("album 1").year_released("2021").build());
        songEntities.add(SongEntity.builder().id(2L).name("Song 2").artist("artist 2").album_name("album 2").year_released("2020").build());
        when(songRepositoryMock.findAll()).thenReturn(songEntities);
        List<Song> expectedSongs = songEntities.stream().map(SongConverter::convert).collect(Collectors.toList());
        GetAllSongsResponse expectedResponse = GetAllSongsResponse.builder().songs(expectedSongs).build();

        // Act
        GetAllSongsResponse actualResponse = getSongsUseCase.getSongs();

        // Assert
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getSongs());
        assertEquals(expectedResponse.getSongs().size(), actualResponse.getSongs().size());
        assertEquals(expectedResponse.getSongs(), actualResponse.getSongs());
    }
}
