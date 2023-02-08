package app.domain.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveSongFromPlaylistRequest {
    private Long playlistId;
    private Long songId;
    private String accessToken;
}
