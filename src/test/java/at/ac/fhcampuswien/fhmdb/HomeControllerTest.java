package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.Test.*;
import static at.ac.fhcampuswien.fhmdb.model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    @Nested
    class SortMovies{
        @Test
        void sortAscending() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = new ArrayList<>(testMovies);
            testController.sortAscending(actual);

            assertEquals(expected, actual);
        }
        @Test
        void sortDescending() {
            List<Movie> expected = new ArrayList<>();
            for (int i = testMovies.size() - 1; i >= 0; i--) {
                expected.add(testMovies.get(i));
            }

            List<Movie> actual = new ArrayList<>(testMovies);
            testController.sortDescending(actual);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesByTitle{
        @Test
        @DisplayName("Testing Title Search")
        void test_Title_Search() {
            String title = "title2";
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(2));
            List<Movie> temp = HomeController.getMoviesByTitle(title, testMovies);
            assertEquals(expected, temp);
        }
        @Test
        @DisplayName("Testing Title Search with empty String Part 1")
        void test_Title_Search_Void_String_1() {
            String title = "";
            List<Movie> temp = HomeController.getMoviesByTitle(title, testMovies);
            assertSame(temp, testMovies);
        }
        @Test
        @DisplayName("Testing Title Search with empty String Part 2")
        void test_Title_Search_Void_String_2() {
            String title = " ";
            List<Movie> temp = HomeController.getMoviesByTitle(title, testMovies);
            assertSame(temp, testMovies);
        }
    }

    @Nested
    class GetMoviesByGenre{
        @Test
        public void getMoviesByGenre_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(3));

            List<Movie> actual = HomeController.getMoviesByGenre(testMovies, ADVENTURE);

            assertTrue(expected.containsAll(actual));
        }
        @Test
        public void getMoviesByGenre_null() {
            List<Movie> expected = new ArrayList<>();

            List<Movie> actual = HomeController.getMoviesByGenre(testMovies, null);

            assertEquals(actual.size(), 0);
        }
        @Test
        public void getMoviesByGenre_none() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = HomeController.getMoviesByGenre(testMovies, __NONE__);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesByReleaseYear{
        @Test
        public void getMoviesByReleaseYear_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(1));

            List<Movie> actual = HomeController.getMoviesByReleaseYear(testMovies, 1111);

            assertEquals(expected, actual);
        }
        @Test
        public void getMoviesByReleaseYear_zero() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = HomeController.getMoviesByReleaseYear(testMovies, 0);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesByRating{
        @Test
        public void getMoviesByRating_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(2));

            List<Movie> actual = HomeController.getMoviesByRating(testMovies, 2);

            assertEquals(expected, actual);
        }
        @Test
        public void getMoviesByRating_zero() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = HomeController.getMoviesByRating(testMovies, 0);

            assertEquals(expected, actual);
        }
    }
}
