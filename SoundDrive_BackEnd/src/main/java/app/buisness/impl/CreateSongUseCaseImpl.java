package app.buisness.impl;

import app.buisness.CreateSongUseCase;
import app.buisness.exception.UnauthorizedDataAccessException;
import app.domain.AccessToken;
import app.domain.Request.CreateSongRequest;
import app.domain.Response.CreateSongResponse;
import app.persistence.Entity.RoleEnum;
import app.persistence.Entity.SongEntity;
import app.persistence.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateSongUseCaseImpl implements CreateSongUseCase {
    private SongRepository songRepository;

    public SongEntity saveNewSong(CreateSongRequest request) {
        SongEntity newSong = SongEntity.builder()
                .name(request.getName())
                .artist(request.getArtist())
                .album_name(request.getAlbum_name())
                .year_released(request.getYear_released())
                .build();
        return songRepository.save(newSong);
    }

    @Override
    public CreateSongResponse createSong(CreateSongRequest request) {

        SongEntity savedSong = saveNewSong(request);

        return CreateSongResponse.builder()
                .id(savedSong.getId())
                .name(savedSong.getName())
                .artist(savedSong.getArtist())
                .year_released(savedSong.getYear_released())
                .album_name(savedSong.getAlbum_name())
                .build();
    }
}
