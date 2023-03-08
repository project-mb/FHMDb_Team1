package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        expected.add(new Movie("test2", "des2", "genre2"));
        List<Movie> temp = Movie.searchTitle(title, movies);
        assertEquals(expected, temp);
    }

    @Test
    @DisplayName("Testing equals override")
    void test_equals_override() {
        List<Movie> temp1 = Movie.initializeMovies();
        List<Movie> temp2 = new ArrayList<>();
        temp2.add(new Movie("test3", "des", "genre"));
        assertTrue(temp1.get(2).equals(temp2.get(0)));
    }
}