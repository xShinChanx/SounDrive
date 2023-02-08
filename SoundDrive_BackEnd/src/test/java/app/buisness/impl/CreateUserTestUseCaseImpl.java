package app.buisness.impl;

import app.buisness.exception.InvalidSongException;
import app.buisness.exception.InvalidUserException;
import app.buisness.impl.CreateUserUseCaseImpl;
import app.domain.Request.CreateUserRequest;
import app.domain.Response.CreateUserResponse;
import app.domain.User;
import app.persistence.Entity.UserEntity;
import app.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserTestUseCaseImpl {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void createAccount(){
        UserEntity saveUser = UserEntity.builder().id(1L).name("Adel").email("adel@gmail.com").password("yeet").build();

        CreateUserRequest request = new CreateUserRequest("Adel", "adel@gmail.com", "yeet");

        when(userRepository.save(any(UserEntity.class))).thenReturn(saveUser);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("yeet");

        CreateUserResponse response = createUserUseCase.createUser(request);

        assertEquals(response.getId(), saveUser.getId());
    }

    @Test
    void createAccountFail(){
        CreateUserRequest request = new CreateUserRequest("Adel", "adel@gmail.com", "yeet");

        when(userRepository.existsByEmail("adel@gmail.com")).thenReturn(true);

        assertThrows(InvalidUserException.class, () -> createUserUseCase.createUser(request));
        verify(userRepository).existsByEmail("adel@gmail.com");

    }
}
