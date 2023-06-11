package at.ac.fhcampuswien.fhmdb.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class URLBuilderTest {
    @Test
    void urlBuilder_basic() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather&genre=DRAMA";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .query("The Godfather")
                .genre("DRAMA")
                .build()
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void urlBuilder_queryOnlyWithGenreIsEmptyString() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather&genre=";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .query("The Godfather")
                .genre("")
                .build()
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void urlBuilder_requestURLOnly() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .build()
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void urlBuilder_queryOnlyWithGenreIsNONE() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .query("The Godfather")
                .genre("__NONE__")
                .build()
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void urlBuilder_genreOnly() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies?genre=ROMANCE";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .genre("ROMANCE")
                .build()
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void URLBuilder_releaseYearOnly() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies?releaseYear=2002";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .releaseYear("2002")
                .build()
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void URLBuilder_ratingFromOnly() {
        String expected = "http://prog2.fh-campuswien.ac.at/movies?rating=8";

        String actual = new URLBuilder()
                .requestURL(MovieAPI.MOVIES_ENDPOINT)
                .ratingFrom("8")
                .build()
                .toString();

        assertEquals(expected, actual);
    }
}
