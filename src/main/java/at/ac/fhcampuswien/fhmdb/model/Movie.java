package at.ac.fhcampuswien.fhmdb.model;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.model.Genre.*;

public class Movie implements Comparable<Movie>{
    public final String id;
    public final String title;
    public final String description;
    public final EnumSet<Genre> genres;
    public final int releaseYear;
    public final String imgUrl;
    public final int lengthInMinutes;
    public final String[] directors;
    public final String[] writers;
    public final String[] mainCast;
    public final float rating;

    public Movie(String title, String description, Genre... genres) {
        this("", title, EnumSet.copyOf(Arrays.asList(genres)), 0, description, "", 0, null, null, null, 0);
    }

    public Movie(String id, String title, EnumSet<Genre> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, String[] directors, String[] writers, String[] mainCast, int rating) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.description = description;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    /*public String getDescription() {
        return description;
    }
    public EnumSet<Genre> getGenres() { return genres; }*/


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
