package at.ac.fhcampuswien.fhmdb.dataLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatchlistEntityTest {
    @Nested
    class GenresToString {
        @Test
        public void genresToString_basic() {
            String expected = "ADVENTURE,CRIME,ROMANCE";

            String actual = new WatchlistEntity().genresToString(EnumSet.of(ADVENTURE, CRIME, ROMANCE));

            assertEquals(expected, actual);
        }
        @Test
        public void genresToString_genre_is_null() {
            String expected = "";

            String actual = new WatchlistEntity().genresToString(null);

            assertEquals(expected, actual);
        }
        @Test
        public void genresToString_genre_is_none() {
            String expected = "";

            String actual = new WatchlistEntity().genresToString(EnumSet.of(__NONE__));

            assertEquals(expected, actual);
        }
        @Test
        public void genresToString_genre_has_none() {
            String expected = "ADVENTURE,ROMANCE";

            String actual = new WatchlistEntity().genresToString(EnumSet.of(ADVENTURE, __NONE__, ROMANCE));

            assertEquals(expected, actual);
        }
    }
}
