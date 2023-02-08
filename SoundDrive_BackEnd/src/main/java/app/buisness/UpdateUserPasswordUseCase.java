package app.buisness;

import app.domain.Request.UpdateUserPasswordRequest;
import app.domain.Response.UpdateUserPasswordResponse;

public interface UpdateUserPasswordUseCase {

    UpdateUserPasswordResponse updateUser(UpdateUserPasswordRequest request);
}
