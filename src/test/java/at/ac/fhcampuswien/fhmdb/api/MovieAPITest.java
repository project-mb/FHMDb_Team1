package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.BaseTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieAPITest {
    @Nested
    class RequestGenerator {
        @Test
        void requestGenerator_basic() {
            String expected = queryTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "The Godfather", DRAMA, "", "");

            assertEquals(expected, actual);
        }

//        @Test
//        void requestGenerator_queryOnly_withGenreIs_null() {
//            String expected = queryTest;
//
//            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "The Godfather", null, "", "");
//
//            assertEquals(expected, actual);
//        }

        @Test
        void requestGenerator_queryOnlyWithGenreIsNONE() {
            String expected = queryTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "The Godfather", __NONE__, "", "");

            assertEquals(expected, actual);
        }

        @Test
        void requestGenerator_genreOnly() {
            String expected = genreTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "", ROMANCE, "", "");

            assertEquals(expected, actual);
        }
    }

    @Nested
    class ResponseParser {
        @Test
        void responseParser_basic() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = MovieAPI.responseParser(jsonTest);

            assertEquals(expected, actual);
        }

        @Test
        void responseParser_emptyArray() {
            List<Movie> expected = new ArrayList<>();

            List<Movie> actual = MovieAPI.responseParser("[]");

            assertEquals(expected, actual);
        }

        @Test
        void responseParser_empty() {
            List<Movie> expected = null;

            List<Movie> actual = MovieAPI.responseParser("");

            assertEquals(expected, actual);
        }
        @Test
        void responseParser_notJson() {
            List<Movie> expected = null;

            List<Movie> actual = MovieAPI.responseParser("asdf");

            assertEquals(expected, actual);
        }
    }
}
