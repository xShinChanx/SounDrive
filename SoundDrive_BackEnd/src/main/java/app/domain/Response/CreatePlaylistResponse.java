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
public class CreatePlaylistResponse {
    private Long playlistId;
    private String name;
}
