package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.exception.InvalidUserException;
import app.buisness.impl.UpdateUserPasswordUseCaseImpl;
import app.domain.AccessToken;
import app.domain.Request.UpdateUserPasswordRequest;
import app.persistence.Entity.UserEntity;
import app.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateUserTestUseCaseImpl {
    @Mock
    UserRepository userRepositoryMock = mock(UserRepository.class);
    @Mock
    AccessToken accessToken;
    @InjectMocks
    UpdateUserPasswordUseCaseImpl updateUserPasswordUseCase;
    @Mock
    PasswordEncoder encoder;
    @Mock
    AccessTokenDecoder tokenDecoder;

    @BeforeEach
    public void setUp() {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        accessToken = AccessToken.builder().userId(1L).roles(roles).subject("testSubject").build();
        updateUserPasswordUseCase = new UpdateUserPasswordUseCaseImpl(userRepositoryMock, encoder ,tokenDecoder);
    }

    @Test
    void updateUser_ShouldUpdatedUser(){
        //Arrange
        UpdateUserPasswordRequest request = UpdateUserPasswordRequest.builder().userId(1L).password("yolo").build();

        Optional<UserEntity> userOptional = Optional.ofNullable(UserEntity.builder().id(1L).email("ruko@mail.com").password("oldYolo").userRoles(null).build());

        when(userRepositoryMock.findById(1L)).thenReturn(userOptional);
        when(encoder.encode(anyString())).thenReturn("yolo");

        //Act
        updateUserPasswordUseCase.updateUser(request);

        //Assert
        assertEquals(request.getPassword(), userOptional.get().getPassword());
    }

    @Test
    void updateUserShouldThrowException() {
        //Arrange
        Optional<UserEntity> userOptional = Optional.empty();
        when(userRepositoryMock.findById(1L)).thenReturn(userOptional);
        UpdateUserPasswordRequest request = UpdateUserPasswordRequest.builder().userId(1L).password("yeet").accessToken("ahhh").build();
        // Act & Assert
        assertThrows(InvalidUserException.class, () -> updateUserPasswordUseCase.updateUser(request));
        verify(userRepositoryMock).findById(1L);
    }

}
