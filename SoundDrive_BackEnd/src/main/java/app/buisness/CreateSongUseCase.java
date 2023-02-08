package app.buisness;

import app.domain.Request.CreateSongRequest;
import app.domain.Response.CreateSongResponse;
import app.persistence.Entity.SongEntity;

public interface CreateSongUseCase {

    CreateSongResponse createSong(CreateSongRequest request);
}
