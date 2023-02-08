package app.buisness;

import app.domain.Request.DeleteUserRequest;
import app.domain.Response.DeleteUserResponse;

public interface DeleteUserUseCase {
    DeleteUserResponse deleteUser(DeleteUserRequest request);
}
