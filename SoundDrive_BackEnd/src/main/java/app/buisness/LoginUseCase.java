package app.buisness;

import app.domain.Request.LoginRequest;
import app.domain.Response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
