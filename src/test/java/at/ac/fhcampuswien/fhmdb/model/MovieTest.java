package at.ac.fhcampuswien.fhmdb.model;

import at.ac.fhcampuswien.fhmdb.HomeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    private final HomeController controller = new HomeController();

    private List<Movie> movies = new ArrayList<>(); {
        movies.add(new Movie("test1", "des1", ACTION));
        movies.add(new Movie("test2", "des2", ACTION));
        movies.add(new Movie("test3", "des3", ACTION));
    }

    @Test
    @DisplayName("Testing Title Search")
    void test_Title_Search() {
        String title = "test2";
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("test2", "des2", ACTION));
        List<Movie> temp = Movie.searchTitle(title, movies);
        assertEquals(expected, temp);
    }

    @Test
    @DisplayName("Testing Title Search with empty String Part 1")
    void test_Title_Search_Void_String_1() {
        String title = "";
        List<Movie> temp = Movie.searchTitle(title, movies);
        assertTrue(temp == movies);
    }

    @Test
    @DisplayName("Testing Title Search with empty String Part 2")
    void test_Title_Search_Void_String_2() {
        String title = " ";
        List<Movie> temp = Movie.searchTitle(title, movies);
        assertTrue(temp == movies);
    }

    @Test
    @DisplayName("Testing equals override")
    void test_equals_override() {
        List<Movie> temp1 = new ArrayList<>();
        temp1.add(new Movie("test3", "des3", ACTION));
        assertTrue(temp1.get(0).equals(movies.get(2)));
    }

    @Test
    public void getMoviesByGenre() {
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("test2", "des2", ADVENTURE, BIOGRAPHY)); //new Movie("test2", "des2", ADVENTURE, BIOGRAPHY)
        expected.add(new Movie("test4", "des4", ADVENTURE)); //new Movie("test4", "des4", ADVENTURE)
        List<Movie> actual = Movie.getMoviesByGenre(controller.allMovies, ADVENTURE);

        assertTrue(expected.containsAll(actual));
    }
}
