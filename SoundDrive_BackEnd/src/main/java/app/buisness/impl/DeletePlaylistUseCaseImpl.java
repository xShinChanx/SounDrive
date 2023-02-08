package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.DeletePlaylistUseCase;
import app.buisness.exception.InvalidUserException;
import app.domain.Request.DeletePlaylistRequest;
import app.domain.Response.DeletePlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeletePlaylistUseCaseImpl implements DeletePlaylistUseCase {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Override
    public DeletePlaylistResponse deletePlaylist(DeletePlaylistRequest request) {

        this.playlistRepository.deleteById(request.getPlaylistId());
        return new DeletePlaylistResponse("Successfully Deleted");
    }

}
