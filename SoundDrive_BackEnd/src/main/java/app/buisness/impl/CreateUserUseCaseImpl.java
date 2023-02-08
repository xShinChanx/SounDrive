package app.buisness.impl;

import app.buisness.CreateUserUseCase;
import app.buisness.exception.InvalidUserException;
import app.domain.Request.CreateUserRequest;
import app.domain.Response.CreateUserResponse;
import app.persistence.Entity.RoleEnum;
import app.persistence.Entity.UserEntity;
import app.persistence.Entity.UserRoleEntity;
import app.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidUserException("email already in use");
        }

        UserEntity saveUser = saveNewUser(request);

        return CreateUserResponse.builder().id(saveUser.getId()).build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.USER)
                        .build()));
        return userRepository.save(newUser);
    }
}
