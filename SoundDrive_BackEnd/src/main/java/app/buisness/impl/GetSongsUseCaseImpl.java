package app.buisness.impl;

import app.buisness.GetSongsUseCase;
import app.domain.Response.GetAllSongsResponse;
import app.domain.Song;
import app.persistence.Entity.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.persistence.SongRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSongsUseCaseImpl implements GetSongsUseCase {
    SongRepository songRepository;
    @Override
    public GetAllSongsResponse getSongs() {
        List<SongEntity> results;
        results = songRepository.findAll();

        List<Song> songs = results
                .stream()
                .map(SongConverter::convert)
                .toList();
        return GetAllSongsResponse.builder().songs(songs).build();
    }
}


