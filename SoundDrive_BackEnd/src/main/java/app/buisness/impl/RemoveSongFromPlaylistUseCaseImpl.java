package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.RemoveSongFromPlaylistUseCase;
import app.buisness.exception.InvalidPlaylistException;
import app.buisness.exception.InvalidSongException;
import app.buisness.exception.InvalidUserException;
import app.domain.Request.RemoveSongFromPlaylistRequest;
import app.domain.Response.RemoveSongFromPlaylistResponse;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.PlaylistSongEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.PlaylistRepository;
import app.persistence.PlaylistSongRepository;
import app.persistence.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RemoveSongFromPlaylistUseCaseImpl implements RemoveSongFromPlaylistUseCase {
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private PlaylistSongRepository playlistSongRepository;

    @Override
    public RemoveSongFromPlaylistResponse AddSongToPlaylist(RemoveSongFromPlaylistRequest request) {
        Optional<PlaylistEntity> playlist = playlistRepository.findById(request.getPlaylistId());

        if(playlist.isEmpty()){
            throw new InvalidPlaylistException("PLAYLIST DOESN'T EXIST");
        }

        Optional<SongEntity> song = songRepository.findById(request.getSongId());

        if(song.isEmpty()){
            throw new InvalidSongException("SONG DOESN'T EXIST");
        }

        if(!CheckIfThereSongAlreadyExist(request.getSongId(), PlaylistEntityConverter.convert(playlist))){
            throw new InvalidPlaylistException("SONG DOES NOT EXIST IN THE PLAYLIST");
        }

        PlaylistSongEntity playlistSong = playlistSongRepository.findByPlaylistAndSong(PlaylistEntityConverter.convert(playlist), SongEntityConverter.convert(song));
        playlistSongRepository.delete(playlistSong);

        return RemoveSongFromPlaylistResponse.builder().playlistId(playlist.get().getID()).build();
    }

    public boolean CheckIfThereSongAlreadyExist(long songID, PlaylistEntity playlist){
        for (SongEntity s:playlist.getSongs()) {
            if(s.getId() == songID){
                return true;
            }
        }
        return false;
    }
}
