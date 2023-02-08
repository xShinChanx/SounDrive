package app.persistence.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "playlist_song")
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PlaylistEntity playlist;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private SongEntity song;
}