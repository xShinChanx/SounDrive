package app.buisness;

import app.domain.Request.GetPlaylistSongsByIdRequest;
import app.domain.Response.GetPlaylistSongsByIdResponse;

public interface GetPlaylistSongsByIdUseCase {

    GetPlaylistSongsByIdResponse getSongs(GetPlaylistSongsByIdRequest request);
}
