package app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    private Long ID;
    private String description;
    private String name;
    private Long playlist_owner_id;
    private List<Song> songs;
}
