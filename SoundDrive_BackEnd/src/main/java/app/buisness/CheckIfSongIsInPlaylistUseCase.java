package app.buisness;

import app.domain.Request.CheckIfSongIsInPlaylistRequest;
import app.domain.Response.CheckIfSongIsInPlaylistResponse;

public interface CheckIfSongIsInPlaylistUseCase {
    CheckIfSongIsInPlaylistResponse checkIfSongIsInPlaylist(CheckIfSongIsInPlaylistRequest request);
}
