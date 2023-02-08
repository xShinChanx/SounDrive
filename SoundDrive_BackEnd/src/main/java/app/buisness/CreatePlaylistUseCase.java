package app.buisness;

import app.domain.Request.CreatePlaylistRequest;
import app.domain.Response.CreatePlaylistResponse;
import app.persistence.Entity.PlaylistEntity;

public interface CreatePlaylistUseCase {
    PlaylistEntity saveNewPlaylist(CreatePlaylistRequest request);
    CreatePlaylistResponse createPlaylist(CreatePlaylistRequest request);
}
