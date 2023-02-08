package app.buisness.impl;

import app.buisness.GetSongBasedOnIdUseCase;
import app.buisness.exception.InvalidSongException;
import app.domain.Request.GetSongBasedOnIdRequest;
import app.domain.Response.GetSongBasedOnIdResponse;
import app.persistence.Entity.SongEntity;
import app.persistence.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetSongBasedOnIdUseCaseImpl implements GetSongBasedOnIdUseCase {

    SongRepository songRepository;

    @Override
    public GetSongBasedOnIdResponse getSong(GetSongBasedOnIdRequest request) {
        Optional<SongEntity> song = songRepository.findById(request.getSongId());

        if(song.isEmpty()){
            throw new InvalidSongException("SONG DOESN'T EXIST");
        }

        return GetSongBasedOnIdResponse.builder().songId(song.get().getId()).name(song.get().getName()).album_name(song.get().getAlbum_name()).artist(song.get().getArtist()).year_released(song.get().getYear_released()).build();
    }
}
