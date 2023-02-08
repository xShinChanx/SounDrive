package app.persistence.Entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "song")
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @Column(name = "artist")
    @Length(max = 50)
    private String artist;

    @Column(name = "album_name")
    @Length(max = 50)
    private String album_name;

    @Column(name = "year_released")
    @Length(max = 50)
    private String year_released;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "songs")
    private List<PlaylistEntity> playlist;
}
