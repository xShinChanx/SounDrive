package app.buisness.impl;

import app.buisness.CheckIfUserHasPlaylistUseCase;
import app.domain.Request.CheckIfUserHasPlaylistRequest;
import app.domain.Response.CheckIfUserHasPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CheckIfUserHasPlaylistUseCaseImpl implements CheckIfUserHasPlaylistUseCase {

    PlaylistRepository playlistRepository;
    @Override
    public CheckIfUserHasPlaylistResponse checkIfUserHasPlaylist(CheckIfUserHasPlaylistRequest request){
        List<PlaylistEntity> playlists = playlistRepository.findByPlaylistOwnerId(request.getUserId());

        if (playlists.isEmpty()) {
            return CheckIfUserHasPlaylistResponse.builder().playlistId(0L).build();
        }

        PlaylistEntity playlist = playlists.get(0);
        return CheckIfUserHasPlaylistResponse.builder().playlistId(playlist.getID()).build();
    }
}
