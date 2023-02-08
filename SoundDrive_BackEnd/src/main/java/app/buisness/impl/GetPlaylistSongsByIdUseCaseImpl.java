package app.buisness.impl;

import app.buisness.GetPlaylistSongsByIdUseCase;
import app.buisness.exception.InvalidPlaylistException;
import app.domain.Request.GetPlaylistSongsByIdRequest;
import app.domain.Response.GetPlaylistSongsByIdResponse;
import app.domain.Song;
import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import app.persistence.Entity.UserEntity;
import app.persistence.PlaylistRepository;
import app.persistence.PlaylistSongRepository;
import app.persistence.SongRepository;
import app.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPlaylistSongsByIdUseCaseImpl implements GetPlaylistSongsByIdUseCase {
    private PlaylistRepository playlistRepository;
    private PlaylistSongRepository playlistSongRepository;
    private UserRepository userRepository;
    private SongRepository songRepository;
    private List<SongEntity> listOfSongs;
    private List<Song> listOfConvertedSongs;

    @Override
    public GetPlaylistSongsByIdResponse getSongs(GetPlaylistSongsByIdRequest request) {
        clearList();

        if(request.getPlaylistId() == null || playlistRepository.findById(request.getPlaylistId()).isEmpty()){
            throw new InvalidPlaylistException("NO_PLAYLIST_FOUND");
        }

        for(Long id : playlistSongRepository.findSongIdByPlaylistId(request.getPlaylistId())){
            listOfSongs.add(SongEntityConverter.convert(songRepository.findById(id)));
        }
        Optional<PlaylistEntity> playlist = playlistRepository.findById(request.getPlaylistId());

        if(playlist.isEmpty()){
            throw new InvalidPlaylistException("PLAYLIST DOESN'T EXIST");
        }

        UserEntity user = userRepository.findUserById(playlist.get().getPlaylistOwnerId());

        return GetPlaylistSongsByIdResponse.builder().listOfSongs(ConvertSongEntityToSong(listOfSongs)).playlistOwner(user.getName()).playlistName(playlist.get().getName()).description(playlist.get().getDescription()).build();
    }

    public List<Song> ConvertSongEntityToSong(List<SongEntity> listOfSongEntity){

        for(SongEntity song: listOfSongEntity){
            listOfConvertedSongs.add(SongConverter.convert(song));
        }
        return listOfConvertedSongs;
    }

    public void clearList(){
        if((long) listOfSongs.size() > 0){
            listOfSongs.clear();
        }

        if((long) listOfConvertedSongs.size() > 0){
            listOfConvertedSongs.clear();
        }
    }
}
