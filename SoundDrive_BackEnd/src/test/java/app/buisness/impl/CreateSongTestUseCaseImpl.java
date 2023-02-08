package app.buisness.impl;

import app.buisness.impl.CreateSongUseCaseImpl;
import app.buisness.impl.CreateUserUseCaseImpl;
import app.domain.Request.CreateSongRequest;
import app.domain.Response.CreateSongResponse;
import app.domain.Response.CreateUserResponse;
import app.persistence.Entity.SongEntity;
import app.persistence.Entity.UserEntity;
import app.persistence.SongRepository;
import app.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateSongTestUseCaseImpl {
    @Mock
    private SongRepository songRepository;
    @InjectMocks
    private CreateSongUseCaseImpl createSongUseCase;

    @Test
    void createSong(){
        SongEntity song = SongEntity.builder().name("TestSong").year_released("2020 Jan").album_name("TestAlbum").artist("TestArtist").build();
        CreateSongRequest request = new CreateSongRequest("TestSong","TestArtist","TestAlbum", "2020 Jan");

        when(songRepository.save(any(SongEntity.class))).thenReturn(song);

        CreateSongResponse response = createSongUseCase.createSong(request);

        assertEquals(response.getId(), song.getId());
    }
}
