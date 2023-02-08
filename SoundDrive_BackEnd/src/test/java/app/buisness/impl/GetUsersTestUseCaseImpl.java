package app.buisness.impl;

import app.buisness.impl.GetUsersUseCaseImpl;
import app.buisness.impl.UserConverter;
import app.domain.Response.GetAllUsersResponse;
import app.domain.User;
import app.persistence.Entity.UserEntity;
import app.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUsersTestUseCaseImpl {
    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    GetUsersUseCaseImpl getUsersUseCase;

    @Test
    void getUsersTest() {
        // Arrange
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(UserEntity.builder().id(1L).name("User 1").email("user1@example.com").password("password1").build());
        userEntities.add(UserEntity.builder().id(2L).name("User 2").email("user2@example.com").password("password2").build());
        when(userRepositoryMock.findAll()).thenReturn(userEntities);
        List<User> expectedUsers = userEntities.stream().map(UserConverter::convert).collect(Collectors.toList());
        GetAllUsersResponse expectedResponse = GetAllUsersResponse.builder().users(expectedUsers).build();

        // Act
        GetAllUsersResponse actualResponse = getUsersUseCase.getUsers();

        // Assert
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getUsers());
        assertEquals(expectedResponse.getUsers().size(), actualResponse.getUsers().size());
        assertEquals(expectedResponse.getUsers(), actualResponse.getUsers());
    }
}

