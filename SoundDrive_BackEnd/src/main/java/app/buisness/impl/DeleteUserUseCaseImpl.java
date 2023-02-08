package app.buisness.impl;

import app.buisness.DeleteUserUseCase;
import app.domain.Request.DeleteUserRequest;
import app.domain.Response.DeleteUserResponse;
import app.persistence.UserRepository;
import app.persistence.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        this.userRepository.deleteById(request.getUserId());
        return new DeleteUserResponse ("Successfully Deleted");
    }
}
