package app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private Long id;
    private String name;
    private String artist;
    private String album_name;
    private String year_released;
}
