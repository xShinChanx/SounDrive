package app.domain;

import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.SongEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSong {
    private Long id;
    private PlaylistEntity playlist;
    private SongEntity song;
}
