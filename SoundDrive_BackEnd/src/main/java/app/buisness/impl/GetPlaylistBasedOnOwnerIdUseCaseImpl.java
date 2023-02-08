package app.buisness.impl;

import app.buisness.GetPlaylistBasedOnOwnerIdUseCase;
import app.domain.Playlist;
import app.domain.Request.GetPlaylistBasedOnOwnerIdRequest;
import app.domain.Response.GetPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPlaylistBasedOnOwnerIdUseCaseImpl implements GetPlaylistBasedOnOwnerIdUseCase {

    private PlaylistRepository playlistRepository;

    @Override
    public GetPlaylistResponse getPlaylist(GetPlaylistBasedOnOwnerIdRequest request) {
        List<PlaylistEntity> playlists = playlistRepository.findByPlaylistOwnerId(request.playlistOwnerId);

        List<Playlist> convertedPlaylists = playlists
                .stream()
                .map(PlaylistConverter::convert)
                .toList();
        return GetPlaylistResponse.builder().playlists(convertedPlaylists).build();
    }
}
