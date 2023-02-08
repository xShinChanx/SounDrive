package app.buisness;

import app.domain.Request.CheckIfUserHasPlaylistRequest;
import app.domain.Response.CheckIfUserHasPlaylistResponse;

public interface CheckIfUserHasPlaylistUseCase {

    CheckIfUserHasPlaylistResponse checkIfUserHasPlaylist(CheckIfUserHasPlaylistRequest request);
}
