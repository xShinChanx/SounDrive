package app.domain.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongRequest {
    private String name;
    private String artist;
    private String album_name;
    private String year_released;
}
