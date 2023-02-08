package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.UpdateUserPasswordUseCase;
import app.buisness.exception.InvalidUserException;
import app.buisness.exception.UnauthorizedDataAccessException;
import app.domain.AccessToken;
import app.domain.Request.UpdateUserPasswordRequest;
import app.domain.Response.UpdateUserPasswordResponse;
import app.persistence.Entity.UserEntity;
import app.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserPasswordUseCaseImpl implements UpdateUserPasswordUseCase {
    @Autowired
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AccessTokenDecoder decoder;

    @Transactional
    @Override
    public UpdateUserPasswordResponse updateUser(UpdateUserPasswordRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            throw new InvalidUserException("USER_ID_INVALID");
        }
        UserEntity user = userOptional.get();

        updateFields(request, user);

        return new UpdateUserPasswordResponse("Password Changed");
    }

    private void updateFields(UpdateUserPasswordRequest request, UserEntity user) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
