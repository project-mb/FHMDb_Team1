package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.model.Genre.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {
    @Test
    void sortAscending() {
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("test1", "des1", ACTION));
        expected.add(new Movie("test2", "des2", ADVENTURE, BIOGRAPHY));
        expected.add(new Movie("test3", "des3", ANIMATION));
        expected.add(new Movie("test4", "des4", ADVENTURE));
        controller.sortAscending();
        assertEquals(expected, controller.filteredMovies);
    }
    @Test
    void sortDescending() {
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("test4", "des4", ADVENTURE));
        expected.add(new Movie("test3", "des3", ANIMATION));
        expected.add(new Movie("test2", "des2", ADVENTURE, BIOGRAPHY));
        expected.add(new Movie("test1", "des1", ACTION));
        controller.sortDescending();
        assertEquals(expected, controller.filteredMovies);
    }
}
