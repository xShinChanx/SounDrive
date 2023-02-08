package app.buisness;

import app.domain.Request.RemoveSongFromPlaylistRequest;
import app.domain.Response.RemoveSongFromPlaylistResponse;

public interface RemoveSongFromPlaylistUseCase {
    RemoveSongFromPlaylistResponse AddSongToPlaylist(RemoveSongFromPlaylistRequest request);

}
