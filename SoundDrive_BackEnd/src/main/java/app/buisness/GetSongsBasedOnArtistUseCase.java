package app.buisness;

import app.domain.Request.GetSongBasedOnArtistRequest;
import app.domain.Response.GetSongBasedOnArtistResponse;

public interface GetSongsBasedOnArtistUseCase {
    GetSongBasedOnArtistResponse getSongs (GetSongBasedOnArtistRequest request);

}
