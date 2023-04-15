package at.ac.fhcampuswien.fhmdb.model;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.model.Genre.*;

public class Movie implements Comparable<Movie> {
    private final String id;
    private final String title;
    private final EnumSet<Genre> genres;
    private final int releaseYear;
    private final String description;
    private final String imgUrl;
    private final int lengthInMinutes;
    private final String[] directors;
    private final String[] writers;
    private final String[] mainCast;
    private final int rating;

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

    public String getId() { return id; }
    public String getTitle() { return title; }
    public EnumSet<Genre> getGenres() { return genres; }
    public int getReleaseYear() { return releaseYear; }
    public String getDescription() { return description; }
    public String getImgUrl() { return imgUrl; }
    public int getLengthInMinutes() { return lengthInMinutes; }
    public String[] getDirectors() { return directors; }
    public String[] getWriters() { return writers; }
    public String[] getMainCast() { return mainCast; }
    public int getRating() { return rating; }

    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();

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
