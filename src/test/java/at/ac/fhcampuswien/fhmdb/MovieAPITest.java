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
    class urlBuilder{
        @Test
        void urlBuilder_basic(){
            String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather&genre=DRAMA";

            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "DRAMA");

            assertEquals(expected, actual);
        }

        @Test
        void urlBuilder_queryOnlyWithGenreIsEmptyString(){
            String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather";

            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "");

            assertEquals(expected, actual);
        }

        @Test
        void urlBuilder_queryOnlyWithGenreIsNONE(){
            String expected = "http://prog2.fh-campuswien.ac.at/movies?query=The%20Godfather";

            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "__NONE__");

            assertEquals(expected, actual);
        }

        @Test
        void urlBuilder_genreOnly(){
            String expected = "http://prog2.fh-campuswien.ac.at/movies?genre=ROMANCE";

            String actual = MovieAPI.urlBuilder(MovieAPI.MOVIES_ENDPOINT, "", "ROMANCE");

            assertEquals(expected, actual);
        }
    }

    @Nested
    class RequestGenerator{
        @Test
        void requestGenerator_basic(){
            String expected = queryTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "DRAMA");

            assertEquals(expected, actual);
        }

        @Test
        void requestGenerator_queryOnly_withGenreIs_emptyString(){
            String expected = queryTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "");

            assertEquals(expected, actual);
        }

        @Test
        void requestGenerator_queryOnlyWithGenreIsNONE(){
            String expected = queryTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "The Godfather", "__NONE__");

            assertEquals(expected, actual);
        }

        @Test
        void requestGenerator_genreOnly(){
            String expected = genreTest;

            String actual = MovieAPI.requestGenerator(MovieAPI.MOVIES_ENDPOINT, "", "ROMANCE");

            assertEquals(expected, actual);
        }
    }

    @Nested
    class ResponseParser{
        @Test
        void responseParser_basic(){
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = MovieAPI.responseParser(jsonTest);

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
