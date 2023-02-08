package app.buisness;

import app.domain.Request.DeletePlaylistRequest;
import app.domain.Response.DeletePlaylistResponse;

public interface DeletePlaylistUseCase {
    DeletePlaylistResponse deletePlaylist(DeletePlaylistRequest request);
}

