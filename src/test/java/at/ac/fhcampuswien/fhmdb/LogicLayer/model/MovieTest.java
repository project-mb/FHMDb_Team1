package at.ac.fhcampuswien.fhmdb.LogicLayer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.BaseTest.testMovies;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    @DisplayName("Testing equals override")
    void test_equals_override() {
        List<Movie> temp1 = new ArrayList<>();
        temp1.add(testMovies.get(2));
        assertEquals(temp1.get(0), testMovies.get(2));
    }
}
