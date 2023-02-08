package app.buisness.impl;

import app.buisness.impl.GetUserUseCaseImpl;
import app.domain.User;
import app.persistence.UserRepository;
import app.persistence.Entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUserTestUseCaseImpl {
    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    GetUserUseCaseImpl getUserUseCase;

    @Test
    void getUserBasedonID(){
        //Arrange
        UserEntity user = UserEntity.builder().id(1L).name("Adel").email("adel@gmail.com").password("yeet").build();
        when(userRepositoryMock.findUserById(1L)).thenReturn(user);
        User expected = User.builder().id(1L).name("Adel").email("adel@gmail.com").password("yeet").build();

        //Act
        User actual = getUserUseCase.getUserBasedOnID(1L);

        //Assert
        assertNotNull(actual);
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getName(),actual.getName());
    }
}