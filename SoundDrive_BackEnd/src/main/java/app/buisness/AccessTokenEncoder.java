package app.buisness;

import app.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
