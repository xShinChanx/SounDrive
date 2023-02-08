package app.domain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSongBasedOnIdResponse {
    Long songId;
    String name;
    String artist;
    String album_name;
    String year_released;
}
