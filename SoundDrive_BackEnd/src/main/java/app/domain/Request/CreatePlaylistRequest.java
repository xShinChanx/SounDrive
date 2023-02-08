package app.domain.Request;

import app.domain.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistRequest {
    private String description;
    private String name;
    private Long userId;
}
