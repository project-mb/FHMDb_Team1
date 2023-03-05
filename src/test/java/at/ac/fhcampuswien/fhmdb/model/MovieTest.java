package at.ac.fhcampuswien.fhmdb.model;

import at.ac.fhcampuswien.fhmdb.HomeController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.model.Genre.ADVENTURE;
import static at.ac.fhcampuswien.fhmdb.model.Genre.BIOGRAPHY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieTest {
    private final HomeController controller = new HomeController();
    @Test
    public void getMoviesByGenre() {
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("test2", "des2", ADVENTURE, BIOGRAPHY)); //new Movie("test2", "des2", ADVENTURE, BIOGRAPHY)
        expected.add(new Movie("test4", "des4", ADVENTURE)); //new Movie("test4", "des4", ADVENTURE)
        List<Movie> actual = controller.getMoviesByGenre(controller.allMovies, ADVENTURE);

        assertTrue(expected.containsAll(actual));
    }
    
}
