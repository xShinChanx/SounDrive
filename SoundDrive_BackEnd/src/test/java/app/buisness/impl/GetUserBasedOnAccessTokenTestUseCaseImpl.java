package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.impl.GetUserBasedOnAccessTokeUseCaseImpl;
import app.domain.AccessToken;
import app.domain.Request.GetUserBasedOnAccessTokenRequest;
import app.domain.Response.GetUserResponse;
import app.persistence.Entity.UserEntity;
import app.persistence.Entity.UserRoleEntity;
import app.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUserBasedOnAccessTokenTestUseCaseImpl {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AccessTokenDecoder decoderMock;
    @InjectMocks
    private GetUserBasedOnAccessTokeUseCaseImpl getUserBasedOnAccessTokeUseCase;

    @Test
    public void getUser_validAccessToken_shouldReturnCorrectResponse() {
        // Arrange
        String accessToken = "valid_access_token";
        Long userId = 1L;
        String role = "user";
        GetUserBasedOnAccessTokenRequest request = new GetUserBasedOnAccessTokenRequest(accessToken);
        UserEntity user = UserEntity.builder().id(userId).name("username").email("email@email.com").password("password").build();
        AccessToken accessTokenInfo = AccessToken.builder().subject(accessToken).roles(Arrays.asList(role)).userId(userId).build();
        when(decoderMock.decode(accessToken)).thenReturn(accessTokenInfo);
        when(userRepositoryMock.findUserById(userId)).thenReturn(user);
        GetUserResponse expectedResponse = GetUserResponse.builder().name(user.getName()).id(user.getId()).email(user.getEmail()).role(role).build();

        // Act
        GetUserResponse actualResponse = getUserBasedOnAccessTokeUseCase.getUser(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }
}
