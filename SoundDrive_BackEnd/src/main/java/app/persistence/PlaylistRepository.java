package app.persistence;

import app.domain.Playlist;
import app.persistence.Entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long>{
    List<PlaylistEntity> findByPlaylistOwnerId(Long playlistOwnerId);

}
