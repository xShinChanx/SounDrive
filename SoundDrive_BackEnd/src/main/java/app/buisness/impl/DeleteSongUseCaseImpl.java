package app.buisness.impl;

import app.buisness.DeleteSongUseCase;
import app.domain.Request.DeleteSongRequest;
import app.domain.Response.DeleteSongResponse;
import app.persistence.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSongUseCaseImpl implements DeleteSongUseCase {
    @Autowired
    private SongRepository songRepository;
    @Override
    public DeleteSongResponse deleteSong(DeleteSongRequest request) {
        songRepository.deleteById(request.getSongId());
        return new DeleteSongResponse("Successfully deleted");
    }
}
