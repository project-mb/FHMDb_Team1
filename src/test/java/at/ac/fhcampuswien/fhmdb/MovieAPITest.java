package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.BaseTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieAPITest {
    @Nested
    class ResponseParser{
        @Test
        void responseParser_basic(){
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = MovieAPI.responseParser(testJson);

            assertEquals(expected, actual);
        }

        @Test
        void responseParser_emptyArray(){
            List<Movie> expected = new ArrayList<>();

            List<Movie> actual = MovieAPI.responseParser("[]");

            assertEquals(expected, actual);
        }

        @Test
        void responseParser_empty(){
            List<Movie> expected = null;

            List<Movie> actual = MovieAPI.responseParser("");

            assertEquals(expected, actual);
        }
        @Test
        void responseParser_notJson(){
            List<Movie> expected = null;

            List<Movie> actual = MovieAPI.responseParser("asdf");

            assertEquals(expected, actual);
        }
    }
}
