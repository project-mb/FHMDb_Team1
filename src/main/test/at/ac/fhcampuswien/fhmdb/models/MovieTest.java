package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.initializeMovies;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    @DisplayName("Testing Title Search")
    void test_Title_Search() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("test1", "des1", "genre1"));
        movies.add(new Movie("test2", "des2", "genre2"));
        movies.add(new Movie("test3", "des3", "genre3"));
        String title = "test2";
        List<Movie> expected = new ArrayList<>();
        List<Movie> temp = Movie.searchTitle(title, movies);
        assertEquals(temp, expected);
    }

    /*@Test
    @DisplayName("Testing Genre Search")
    void test_Genre_Search() {
        //List<Movie> movies = initializeMovies();
        //String genre = "ACTION";
        //List<Movie> expected = searchGenre(genre, movies);
    }*/
}