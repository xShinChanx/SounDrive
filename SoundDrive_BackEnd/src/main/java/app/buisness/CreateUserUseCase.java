package app.buisness;

import app.domain.Request.CreateUserRequest;
import app.domain.Response.CreateUserResponse;

public interface CreateUserUseCase {

    CreateUserResponse createUser(CreateUserRequest request);
}
