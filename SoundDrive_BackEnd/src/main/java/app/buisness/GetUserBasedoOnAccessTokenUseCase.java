package app.buisness;

import app.domain.Request.GetUserBasedOnAccessTokenRequest;
import app.domain.Response.GetUserResponse;

public interface GetUserBasedoOnAccessTokenUseCase {

    GetUserResponse getUser(GetUserBasedOnAccessTokenRequest request);
}
