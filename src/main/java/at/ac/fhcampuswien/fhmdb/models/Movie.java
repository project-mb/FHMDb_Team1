package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Genre.*;

public class Movie {
    private String title;
    private String description;
    private EnumSet<Genre> genres;

    public Movie(String title, String description, Genre... genres) {
        this.title = title;
        this.description = description;
        this.genres = EnumSet.copyOf(Arrays.asList(genres));
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public EnumSet<Genre> getGenres() { return genres; }

    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here

        movies.add(new Movie("test1", "des1", ACTION));
        movies.add(new Movie("test2", "des2", ADVENTURE, BIOGRAPHY));
        movies.add(new Movie("test3", "des3", ANIMATION));

        return movies;
    }
}
