package app.persistence.Entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "playlist")
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "name")
    private String name;

    @Column(name = "playlist_owner_id")
    private Long playlistOwnerId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<SongEntity> songs;
}
