package app.buisness.impl;

import app.buisness.GetPlaylistUseCase;
import app.domain.Playlist;
import app.domain.Response.GetPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPlaylistsUseCaseImpl implements GetPlaylistUseCase {

    PlaylistRepository repository;

    @Override
    public GetPlaylistResponse getPlaylist() {
        List<PlaylistEntity> results;
        results = repository.findAll();

        List<Playlist> playlists = results
                .stream()
                .map(PlaylistConverter::convert)
                .toList();
        return GetPlaylistResponse.builder().playlists(playlists).build();
    }
}
