package app.buisness;

import app.domain.Request.AddSongToPlaylistRequest;
import app.domain.Response.AddSongToPlaylistResponse;

public interface AddSongToPlaylistUseCase {

    AddSongToPlaylistResponse AddSongToPlaylist(AddSongToPlaylistRequest request);
}
