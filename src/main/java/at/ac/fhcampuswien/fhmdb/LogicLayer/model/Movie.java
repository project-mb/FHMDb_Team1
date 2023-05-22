package at.ac.fhcampuswien.fhmdb.LogicLayer.model;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;

import java.util.*;

import static at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre.*;

public class Movie implements Comparable<Movie> {
    public final String id;
    public final String title;
    public final EnumSet<Genre> genres;
    public final int releaseYear;
    public final String description;
    public final String imgUrl;
    public final int lengthInMinutes;
    public final String[] directors;
    public final String[] writers;
    public final String[] mainCast;
    public final double rating;

    public boolean isWatchlisted;

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
        this.isWatchlisted = false;
    }

    public Movie(WatchlistEntity watchlistEntity) {
        String[] blank = {" "};
        this.id = watchlistEntity.getApild();
        this.title = watchlistEntity.getTitle();
        this.genres = stringToGenres(watchlistEntity.getGenres());
        this.releaseYear = watchlistEntity.getReleaseYear();
        this.description = watchlistEntity.getDescription();
        this.imgUrl = watchlistEntity.getImgUrl();
        this.lengthInMinutes = watchlistEntity.getLengthInMinutes();
        this.directors = blank;
        this.writers = blank;
        this.mainCast = blank;
        this.rating = watchlistEntity.getRating();
        this.isWatchlisted = watchlistEntity.isWatchlisted();
    }

    public String getTitle() { return title; }

    public static List<Movie> initializeMovies() {
        List<Movie> movies = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT);

        if (movies == null) {
            movies = new ArrayList<>();
            movies.add(new Movie("id0", "title0", EnumSet.of(__NONE__), 0, "des0", "imgUrl0", 0, new String[]{"director01", "director02"}, new String[]{"writer01", "writer02"}, new String[]{"mainCast01", "mainCast02"}, 0));
            movies.add(new Movie("id1", "title1", EnumSet.of(ACTION), 1111, "des1", "imgUrl1", 1, new String[]{"director11", "director12"}, new String[]{"writer11", "writer12"}, new String[]{"mainCast11", "mainCast12"}, 1));
            movies.add(new Movie("id2", "title2", EnumSet.of(BIOGRAPHY, ANIMATION), 2222, "des2", "imgUrl2", 2, new String[]{"director21", "director22"}, new String[]{"writer21", "writer22"}, new String[]{"mainCast21", "mainCast22"}, 2));
            movies.add(new Movie("id3", "title3", EnumSet.of(ADVENTURE, CRIME, ROMANCE), 3333, "des3", "imgUrl3", 3, new String[]{"director31", "director32"}, new String[]{"writer31", "writer32"}, new String[]{"mainCast31", "mainCast32"}, 3));
        }

        return movies;
    }

    public EnumSet<Genre> stringToGenres(String s) {
        String[] array = s.split(", ");

        return EnumSet.copyOf(Arrays.stream(array).map(Genre::valueOf).toList());
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

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                ", directors=" + Arrays.toString(directors) +
                ", writers=" + Arrays.toString(writers) +
                ", mainCast=" + Arrays.toString(mainCast) +
                ", rating=" + rating +
                '}';
    }
}
