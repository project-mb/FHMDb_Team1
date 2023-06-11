package at.ac.fhcampuswien.fhmdb.LogicLayer.State;

import at.ac.fhcampuswien.fhmdb.BaseTest;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorterTest {

    List<Movie> movieList = new ArrayList<>(BaseTest.testMovies);

    @Test
    void stateTest_startState() {
        Sorter sorter = new Sorter(movieList);

        State expected = new Unsorted(sorter);

        State actual = sorter.state;

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void stateTest_ascendingState() {
        Sorter sorter = new Sorter(movieList);

        State expected = new AscSorted(sorter);
        sorter.state.onSort();

        State actual = sorter.state;

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void stateTest_descendingState() {
        Sorter sorter = new Sorter(movieList);

        State expected = new DescSorted(sorter);
        sorter.state.onSort();
        sorter.state.onSort();

        State actual = sorter.state;

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void stateTest_ascendingAfterDescending() {
        Sorter sorter = new Sorter(movieList);

        State expected = new AscSorted(sorter);
        sorter.state.onSort();
        sorter.state.onSort();
        sorter.state.onSort();

        State actual = sorter.state;

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void sorterTest_Unsorted() {
        Sorter sorter = new Sorter(movieList);

        List<Movie> expected = new ArrayList<>(BaseTest.testMovies);

        List<Movie> actual = sorter.movieList;

        assertEquals(expected, actual);
    }

    @Test
    void sorterTest_Ascending() {
        Sorter sorter = new Sorter(movieList);
        sorter.state.onSort();

        List<Movie> expected = new ArrayList<>(BaseTest.testMoviesAscending);

        List<Movie> actual = sorter.movieList;

        assertEquals(expected, actual);
    }

    @Test
    void sorterTest_Descending() {
        Sorter sorter = new Sorter(movieList);
        sorter.state.onSort();
        sorter.state.onSort();

        List<Movie> expected = new ArrayList<>(BaseTest.testMoviesDescending);

        List<Movie> actual = sorter.movieList;

        assertEquals(expected, actual);
    }
}
