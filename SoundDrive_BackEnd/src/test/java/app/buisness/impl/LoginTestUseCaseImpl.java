package app.buisness.impl;

import app.buisness.AccessTokenEncoder;
import app.buisness.exception.InvalidCredentialsException;
import app.buisness.impl.LoginUseCaseImpl;
import app.domain.AccessToken;
import app.domain.Request.LoginRequest;
import app.domain.Response.LoginResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.RoleEnum;
import app.persistence.Entity.UserEntity;
import app.persistence.Entity.UserRoleEntity;
import app.persistence.PlaylistRepository;
import app.persistence.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginTestUseCaseImpl {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;
    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    public void whenLoginRequestIsValid_ShouldReturnLoginResponse() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("johndoe")
                .password("password123")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("johndoe")
                .password("$2y$12$Z8fh4XL4vF/mGDyU6Iyc6.kU6SzKsT7VHut.8sZbV1vnTcTbTk2f2")
                .userRoles(null)
                .build();

        userEntity.setUserRoles(getRoles(userEntity));

        when(userRepository.findUserByEmail("johndoe")).thenReturn(userEntity);
        when(passwordEncoder.matches("password123", "$2y$12$Z8fh4XL4vF/mGDyU6Iyc6.kU6SzKsT7VHut.8sZbV1vnTcTbTk2f2"))
                .thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessToken.class))).thenReturn("token123");
        List<PlaylistEntity> playlistEntities = new ArrayList<>();
        when(playlistRepository.findByPlaylistOwnerId(1L)).thenReturn(playlistEntities);
        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals("token123", loginResponse.getAccessToken());
    }

    @Test
    public void whenLoginRequestIsInvalid_ShouldThrowInvalidCredentialsException() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("johndoe")
                .password("password123")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("johndoe")
                .password("$2y$12$Z8fh4XL4vF/mGDyU6Iyc6.kU6SzKsT7VHut.8sZbV1vnTcTbTk2f2")
                .userRoles(null)
                .build();

        userEntity.setUserRoles(getRoles(userEntity));

        when(userRepository.findUserByEmail("johndoe")).thenReturn(userEntity);
        when(passwordEncoder.matches("password123", "$2y$12$Z8fh4XL4vF/mGDyU6Iyc6.kU6SzKsT7VHut.8sZbV1vnTcTbTk2f2"))
                .thenReturn(false);

        Assertions.assertThrows(InvalidCredentialsException.class,
                () -> loginUseCase.login(loginRequest));
    }

    private Set<UserRoleEntity> getRoles(UserEntity user) {
        Set<UserRoleEntity> roles = new HashSet<>();
        UserRoleEntity role = UserRoleEntity.builder().role(RoleEnum.USER).user(user).build();
        roles.add(role);
        return roles;
    }
}
