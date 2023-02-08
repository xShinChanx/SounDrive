package app.buisness.impl;

import app.buisness.GetUsersUseCase;
import app.domain.Response.GetAllUsersResponse;
import app.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.persistence.UserRepository;
import app.persistence.Entity.UserEntity;

import java.util.List;
@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    UserRepository  userRepository;
    @Override
    public GetAllUsersResponse getUsers() {
        List<UserEntity> results;
        results = userRepository.findAll();

        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        return GetAllUsersResponse.builder().users(users).build();
    }
}
