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

    private List<at.ac.fhcampuswien.fhmdb.models.Movie> movies = new ArrayList<>(); {
        movies.add(new at.ac.fhcampuswien.fhmdb.models.Movie("test1", "des1", "genre1"));
        movies.add(new at.ac.fhcampuswien.fhmdb.models.Movie("test2", "des2", "genre2"));
        movies.add(new at.ac.fhcampuswien.fhmdb.models.Movie("test3", "des3", "genre3"));
    }

    @Test
    @DisplayName("Testing Title Search")
    void test_Title_Search() {
        String title = "test2";
        List<at.ac.fhcampuswien.fhmdb.models.Movie> expected = new ArrayList<>();
        expected.add(new at.ac.fhcampuswien.fhmdb.models.Movie("test2", "des2", "genre2"));
        List<at.ac.fhcampuswien.fhmdb.models.Movie> temp = at.ac.fhcampuswien.fhmdb.models.Movie.searchTitle(title, movies);
        assertEquals(expected, temp);
    }

    @Test
    @DisplayName("Testing Title Search with empty String Part 1")
    void test_Title_Search_Void_String_1() {
        String title = "";
        List<at.ac.fhcampuswien.fhmdb.models.Movie> temp = at.ac.fhcampuswien.fhmdb.models.Movie.searchTitle(title, movies);
        assertTrue(temp == movies);
    }

    @Test
    @DisplayName("Testing Title Search with empty String Part 2")
    void test_Title_Search_Void_String_2() {
        String title = " ";
        List<at.ac.fhcampuswien.fhmdb.models.Movie> temp = at.ac.fhcampuswien.fhmdb.models.Movie.searchTitle(title, movies);
        assertTrue(temp == movies);
    }

    @Test
    @DisplayName("Testing equals override")
    void test_equals_override() {
        List<at.ac.fhcampuswien.fhmdb.models.Movie> temp1 = at.ac.fhcampuswien.fhmdb.models.Movie.initializeMovies();
        List<at.ac.fhcampuswien.fhmdb.models.Movie> temp2 = new ArrayList<>();
        temp2.add(new at.ac.fhcampuswien.fhmdb.models.Movie("test3", "des", "genre"));
        assertTrue(temp2.get(0).equals(temp1.get(2)));
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
