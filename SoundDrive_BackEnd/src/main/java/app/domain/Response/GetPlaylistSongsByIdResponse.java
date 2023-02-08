package app.domain.Response;

import app.domain.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPlaylistSongsByIdResponse {
    private List<Song> listOfSongs;
    private String playlistOwner;
    private String playlistName;
    private String description;
}
