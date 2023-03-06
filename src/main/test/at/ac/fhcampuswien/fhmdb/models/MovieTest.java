package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.*;

import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.initializeMovies;

public class MovieTest {

    @Test
    @DisplayName("Testing Title Search")
    void test_Title_Search() {
        List<Movie> movies = initializeMovies();
        String title = "test";
        //List<Movie> expected = searchTitle(title, movies);
    }

    @Test
    @DisplayName("Testing Genre Search")
    void test_Genre_Search() {
        List<Movie> movies = initializeMovies();
        String genre = "ACTION";
        //List<Movie> expected = searchGenre(genre, movies);
    }
}