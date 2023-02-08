package app.persistence;

import app.domain.Song;
import app.persistence.Entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, Long> {
    @Query("SELECT s FROM SongEntity s WHERE s.artist = :artist")
    List<SongEntity> findAllSongsByArtist(@Param("artist") String artist);
}
