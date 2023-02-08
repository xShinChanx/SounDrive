package app.buisness;

import app.domain.Request.DeleteSongRequest;
import app.domain.Response.DeleteSongResponse;

public interface DeleteSongUseCase {
    DeleteSongResponse deleteSong(DeleteSongRequest request);
}
