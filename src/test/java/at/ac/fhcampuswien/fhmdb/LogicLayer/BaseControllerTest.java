package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.BaseTest.testMovies;
import static at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre.ADVENTURE;
import static at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre.__NONE__;
import static org.junit.jupiter.api.Assertions.*;

public class BaseControllerTest {
    @Nested
    class GetMoviesByTitle {
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
    class GetMoviesByGenre {
        @Test
        public void getMoviesByGenre_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(3));

            List<Movie> actual = HomeController.getMoviesByGenre(testMovies, ADVENTURE);

            assertTrue(expected.containsAll(actual));
        }
        @Test
        public void getMoviesByGenre_genre_is_null() {
            List<Movie> expected = new ArrayList<>();

            List<Movie> actual = HomeController.getMoviesByGenre(testMovies, null);

            assertEquals(actual.size(), 0);
        }
        @Test
        public void getMoviesByGenre_genre_is_none() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = HomeController.getMoviesByGenre(testMovies, __NONE__);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesByReleaseYear {
        @Test
        public void getMoviesByReleaseYear_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(1));

            List<Movie> actual = HomeController.getMoviesByReleaseYear(testMovies, 1111);

            assertEquals(expected, actual);
        }
        @Test
        public void getMoviesByReleaseYear_releaseYear_is_zero() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = HomeController.getMoviesByReleaseYear(testMovies, 0);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesByRating {
        @Test
        public void getMoviesByRating_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(2));

            List<Movie> actual = HomeController.getMoviesByRating(testMovies, 2);

            assertEquals(expected, actual);
        }
        @Test
        public void getMoviesByRating_rating_is_zero() {
            List<Movie> expected = new ArrayList<>(testMovies);

            List<Movie> actual = HomeController.getMoviesByRating(testMovies, 0);

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMostPopularActor {
        @Test
        void getMostPopularActor_basic() {
            String expected = "mainCast41";

            String actual = HomeController.getMostPopularActor(testMovies);

            assertEquals(expected, actual);
        }

        @Test
        void getMostPopularActor_movies_is_null() {
            String expected = "";

            String actual = HomeController.getMostPopularActor(null);

            assertEquals(expected, actual);
        }

        @Test
        void getMostPopularActor_movies_is_empty() {
            String expected = "";

            String actual = HomeController.getMostPopularActor(new ArrayList<>());

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetLongestMovieTitle {
        @Test
        void getLongestMovieTitle_basic() {
            int expected = 10;

            int actual = HomeController.getLongestMovieTitle(testMovies);

            assertEquals(expected, actual);
        }

        @Test
        void getLongestMovieTitle_movies_is_null() {
            int expected = 0;

            int actual = HomeController.getLongestMovieTitle(null);

            assertEquals(expected, actual);
        }

        @Test
        void getLongestMovieTitle_movies_is_empty() {
            int expected = 0;

            int actual = HomeController.getLongestMovieTitle(new ArrayList<>());

            assertEquals(expected, actual);
        }
    }

    @Nested
    class CountMoviesFrom {
        @Test
        void countMoviesFrom_basic() {
            long expected = 2;

            long actual = HomeController.countMoviesFrom(testMovies, "director41");

            assertEquals(expected, actual);
        }

        @Test
        void countMoviesFrom_movies_is_null() {
            long expected = 0;

            long actual = HomeController.countMoviesFrom(null, "director41");

            assertEquals(expected, actual);
        }

        @Test
        void countMoviesFrom_movies_is_empty() {
            long expected = 0;

            long actual = HomeController.countMoviesFrom(new ArrayList<>(), "director41");

            assertEquals(expected, actual);
        }

        @Test
        void countMoviesFrom_director_is_emptyString() {
            long expected = testMovies.size();

            long actual = HomeController.countMoviesFrom(testMovies, "");

            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetMoviesBetweenYears {
        @Test
        void getMoviesBetweenYears_basic() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(2));
            expected.add(testMovies.get(3));

            List<Movie> actual = HomeController.getMoviesBetweenYears(testMovies, 2222, 3333);

            assertEquals(expected, actual);
        }

        @Test
        void getMoviesBetweenYears_movies_is_null() {
            List<Movie> expected = null;

            List<Movie> actual = HomeController.getMoviesBetweenYears(null, 2222, 3333);

            assertEquals(expected, actual);
        }

        @Test
        void getMoviesBetweenYears_movies_is_empty() {
            List<Movie> expected = null;

            List<Movie> actual = HomeController.getMoviesBetweenYears(new ArrayList<>(), 2222, 3333);

            assertEquals(expected, actual);
        }

        @Test
        void getMoviesBetweenYears_startYear_isLessThanEqual_zero() {
            List<Movie> expected = null;

            List<Movie> actual = HomeController.getMoviesBetweenYears(new ArrayList<>(), 0, 3333);

            assertEquals(expected, actual);
        }

        @Test
        void getMoviesBetweenYears_endYear_isLessThanEqual_zero() {
            List<Movie> expected = null;

            List<Movie> actual = HomeController.getMoviesBetweenYears(new ArrayList<>(), 3333, 0);

            assertEquals(expected, actual);
        }

        @Test
        void getMoviesBetweenYears_startYear_isGreaterThan_endYear() {
            List<Movie> expected = null;

            List<Movie> actual = HomeController.getMoviesBetweenYears(new ArrayList<>(), 3333, 1111);

            assertEquals(expected, actual);
        }

        @Test
        void getMoviesBetweenYears_startYear_is_endYear() {
            List<Movie> expected = new ArrayList<>();
            expected.add(testMovies.get(1));

            List<Movie> actual = HomeController.getMoviesBetweenYears(testMovies, 1111, 1111);

            assertEquals(expected, actual);
        }
    }
}
