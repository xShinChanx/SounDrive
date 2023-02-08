package app.buisness.impl;

import app.buisness.impl.DeleteUserUseCaseImpl;
import app.domain.Request.DeleteUserRequest;
import app.domain.Response.DeleteUserResponse;
import app.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTestUseCaseImpl {
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    public void deleteUser_validUserId_shouldDeleteUser() {
        // Arrange
        Long userId = 1L;
        DeleteUserRequest request = new DeleteUserRequest(userId);
        doNothing().when(userRepositoryMock).deleteById(userId);
        // Act
        DeleteUserResponse response = deleteUserUseCase.deleteUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("Successfully Deleted", response.getMessage());
        verify(userRepositoryMock, times(1)).deleteById(userId);
    }
}
