package app.buisness.impl;

import app.buisness.CheckIfSongIsInPlaylistUseCase;
import app.domain.Request.CheckIfSongIsInPlaylistRequest;
import app.domain.Response.CheckIfSongIsInPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import app.persistence.PlaylistSongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckIfSongIsInPlaylistUseCaseImpl implements CheckIfSongIsInPlaylistUseCase {
    private PlaylistSongRepository playlistSongRepository;

    @Override
    public CheckIfSongIsInPlaylistResponse checkIfSongIsInPlaylist(CheckIfSongIsInPlaylistRequest request) {

        if(playlistSongRepository.existsByPlaylistIDAndSongId(request.getPlaylistId(), request.getSongId())){
            return CheckIfSongIsInPlaylistResponse.builder().isPresent(false).build();
        }
        return CheckIfSongIsInPlaylistResponse.builder().isPresent(true).build();
    }
}
