package app.buisness.impl;

import app.buisness.CreatePlaylistUseCase;
import app.buisness.exception.InvalidPlaylistException;
import app.domain.Request.CreatePlaylistRequest;
import app.domain.Response.CreatePlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreatePlaylistUseCaseImpl implements CreatePlaylistUseCase {
    PlaylistRepository playlistRepository;

    @Override
    public PlaylistEntity saveNewPlaylist(CreatePlaylistRequest request) {
        PlaylistEntity newPlaylist = PlaylistEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .playlistOwnerId(request.getUserId())
                .songs(null)
                .build();
        return playlistRepository.save(newPlaylist);
    }

    @Override
    public CreatePlaylistResponse createPlaylist(CreatePlaylistRequest request) {
        checkIfUserAlreadyHasPlaylist(request.getUserId());
        PlaylistEntity savedPlaylist = saveNewPlaylist(request);

        return CreatePlaylistResponse.builder()
                .name(savedPlaylist.getName())
                .playlistId(savedPlaylist.getID())
                .build();
    }

    public void checkIfUserAlreadyHasPlaylist(Long playlistOwnerId){
        List<PlaylistEntity> playlists = playlistRepository.findByPlaylistOwnerId(playlistOwnerId);
        if (!playlists.isEmpty()) {
            throw new InvalidPlaylistException("USER ALREADY HAS A PLAYLIST");
        }
    }
}
