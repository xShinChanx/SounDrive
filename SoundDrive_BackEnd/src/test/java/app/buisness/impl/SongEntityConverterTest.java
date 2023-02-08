package app.buisness.impl;

import app.buisness.exception.InvalidSongException;
import app.persistence.Entity.SongEntity;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class SongEntityConverterTest {
    @Test
    void shouldFailSongEmpty() {
        Optional<SongEntity> emptyOptional = Optional.empty();
        assertThrows(InvalidSongException.class, () -> SongEntityConverter.convert(emptyOptional),"SONG DOESN'T EXIST");
    }
}