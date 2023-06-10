package at.ac.fhcampuswien.fhmdb.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class URLBuilderTest {
        @Test
        void urlBuilder_basic() {
            String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather&genre=DRAMA";

            String actual = new URLBuilder().requestURL(MovieAPI.MOVIES_ENDPOINT)
                    .query("The Godfather")
                    .genre("DRAMA")
                    .build()
                    .toString();

            assertEquals(expected, actual);
        }

//        @Test
//        void urlBuilder_queryOnlyWithGenreIsEmptyString() {
//            String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather";
//
//            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "", "", "");
//
//            assertEquals(expected, actual);
//        }
//
//        @Test
//        void urlBuilder_queryOnlyWithGenreIsNONE() {
//            String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather";
//
//            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "__NONE__", "", "");
//
//            assertEquals(expected, actual);
//        }
//
//        @Test
//        void urlBuilder_genreOnly() {
//            String expected = "http://prog2.fh-campuswien.ac.at/movies?genre=ROMANCE";
//
//            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "", "ROMANCE", "", "");
//
//            assertEquals(expected, actual);
//        }
}
