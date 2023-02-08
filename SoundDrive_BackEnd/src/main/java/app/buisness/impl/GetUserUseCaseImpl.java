package app.buisness.impl;

import app.buisness.GetUserUseCase;
import app.domain.User;
import app.persistence.Entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.persistence.UserRepository;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Override
    public User getUserBasedOnID(Long userID) {
        UserEntity user = userRepository.findUserById(userID);
        return UserConverter.convert(user);
    }
}
