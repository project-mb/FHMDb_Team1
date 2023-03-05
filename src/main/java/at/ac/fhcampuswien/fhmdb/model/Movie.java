package at.ac.fhcampuswien.fhmdb.model;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.model.Genre.*;

public class Movie implements Comparable<Movie>{
    private final String title;
    private final String description;
    private final EnumSet<Genre> genres;

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
        movies.add(new Movie("test4", "des4", ADVENTURE));


        return movies;
    }
    @Override
    public int compareTo(Movie o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genres, movie.genres);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, description, genres);
    }
}
