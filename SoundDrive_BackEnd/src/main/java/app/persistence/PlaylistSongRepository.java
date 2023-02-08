package app.persistence;

import app.persistence.Entity.PlaylistEntity;
import app.persistence.Entity.PlaylistSongEntity;
import app.persistence.Entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSongEntity, Long> {

    @Query(value = "SELECT s.id FROM Song s INNER JOIN playlist_song ps ON s.id = ps.song_id WHERE ps.playlist_id = :playlistId", nativeQuery = true)
    List<Long> findSongIdByPlaylistId(@Param("playlistId") Long playlistId);

    PlaylistSongEntity findByPlaylistAndSong(PlaylistEntity playlist, SongEntity song);

    boolean existsByPlaylistIDAndSongId(Long playlistID, Long songId);

    List<SongEntity> findByPlaylist(Long playlistId);
}
