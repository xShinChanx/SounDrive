package app.buisness.impl;

import app.buisness.GetSongsBasedOnArtistUseCase;
import app.buisness.exception.InvalidSongException;
import app.domain.Request.GetSongBasedOnArtistRequest;
import app.domain.Response.GetSongBasedOnArtistResponse;
import app.domain.Song;
import app.persistence.Entity.SongEntity;
import app.persistence.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetSongBasedOnArtistUseCaseImpl implements GetSongsBasedOnArtistUseCase {

    SongRepository songRepository;

    @Override
    public GetSongBasedOnArtistResponse getSongs(GetSongBasedOnArtistRequest request) {
        List<SongEntity> songEntities = songRepository.findAllSongsByArtist(request.getArtistName());

        if(songEntities.isEmpty()){
            throw new InvalidSongException("NO SONGS AVAILABLE FROM THIS ARTIST");
        }

        List<Song> songs = new ArrayList<>();

        for (SongEntity songEntity : songEntities) {
            Song song = new Song();
            song.setId(songEntity.getId());
            song.setName(songEntity.getName());
            song.setArtist(songEntity.getArtist());
            song.setYear_released(songEntity.getYear_released());
            song.setAlbum_name(songEntity.getAlbum_name());
            songs.add(song);
        }

        return GetSongBasedOnArtistResponse.builder().listOfSongs(songs).build();
    }
}
