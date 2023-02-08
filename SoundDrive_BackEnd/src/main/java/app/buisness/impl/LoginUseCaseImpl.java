package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.AccessTokenEncoder;
import app.buisness.LoginUseCase;
import app.buisness.exception.InvalidCredentialsException;
import app.domain.AccessToken;
import app.domain.Request.CheckIfUserHasPlaylistRequest;
import app.domain.Request.LoginRequest;
import app.domain.Response.CheckIfUserHasPlaylistResponse;
import app.domain.Response.LoginResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.UserEntity;
import app.persistence.PlaylistRepository;
import app.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    private PlaylistRepository playlistRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findUserByEmail(loginRequest.getEmail());

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
       }

        String accessToken = generateAccessToken(user);

        Long playlistId = checkIfUserHasPlaylist(user.getId());
        return LoginResponse.builder().accessToken(accessToken).playlistId(playlistId).name(user.getName()).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        if(passwordEncoder.matches(rawPassword, encodedPassword)){
            return true;
        }
        return false;
    }

    public String generateAccessToken(UserEntity user) {
        Long userId = user.getId();
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .userId(userId)
                        .build());
    }

    public Long checkIfUserHasPlaylist(Long userId){
        List<PlaylistEntity> playlists = playlistRepository.findByPlaylistOwnerId(userId);

        if (playlists.isEmpty()) {
            return 0L;
        }

        PlaylistEntity playlist = playlists.get(0);
        return playlist.getID();
    }
}
