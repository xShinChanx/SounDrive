package app.buisness.impl;

import app.buisness.AddSongToPlaylistUseCase;
import app.buisness.exception.InvalidPlaylistException;
import app.buisness.exception.InvalidSongException;
import app.buisness.exception.InvalidUserException;
import app.domain.Request.AddSongToPlaylistRequest;
import app.domain.Response.AddSongToPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.PlaylistSongEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import app.persistence.PlaylistSongRepository;
import app.persistence.SongRepository;
import app.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddSongToPlaylistUseCaseImpl implements AddSongToPlaylistUseCase {
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private PlaylistSongRepository playlistSongRepository;

    @Override
    public AddSongToPlaylistResponse AddSongToPlaylist(AddSongToPlaylistRequest request) {

        Optional<PlaylistEntity> playlist = playlistRepository.findById(request.getPlaylistId());

        Optional<SongEntity> song = songRepository.findById(request.getSongId());

        if(song.isEmpty()){
            throw new InvalidSongException("SONG DOESN'T EXIST");
        }
        if(playlist.isEmpty()){
            throw new InvalidPlaylistException("PLAYLIST DOESNT EXIST");
        }

        if(playlistSongRepository.existsByPlaylistIDAndSongId(playlist.get().getID(),song.get().getId())){
            throw new InvalidPlaylistException("SONG_ALREADY_EXIST");
        }

        PlaylistSongEntity playlistSong = new PlaylistSongEntity();
        playlistSong.setPlaylist(PlaylistEntityConverter.convert(playlist));
        playlistSong.setSong(SongEntityConverter.convert(song));

        playlistSongRepository.save(playlistSong);

        return AddSongToPlaylistResponse.builder().name(playlist.get().getName()).build();
    }

}
