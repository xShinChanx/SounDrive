package app.buisness;

import app.domain.Request.GetPlaylistBasedOnOwnerIdRequest;
import app.domain.Response.GetPlaylistResponse;

public interface GetPlaylistBasedOnOwnerIdUseCase {
    GetPlaylistResponse getPlaylist (GetPlaylistBasedOnOwnerIdRequest Request);
}
