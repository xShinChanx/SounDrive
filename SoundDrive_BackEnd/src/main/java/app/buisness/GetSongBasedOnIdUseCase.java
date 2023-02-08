package app.buisness;

import app.domain.Request.GetSongBasedOnIdRequest;
import app.domain.Response.GetSongBasedOnIdResponse;

public interface GetSongBasedOnIdUseCase {

    GetSongBasedOnIdResponse getSong (GetSongBasedOnIdRequest request);
}
