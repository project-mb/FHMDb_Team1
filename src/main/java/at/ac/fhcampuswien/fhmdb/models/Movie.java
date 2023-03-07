package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    private String title;
    private String description;

    //private List<Genre> genres = Genre.genres;
    private String genres;

    public Movie(String title, String description, String genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> searchTitle(String title, List<Movie> movieList) {
        return movieList.stream().filter(movie -> movie.getTitle().contains(title)).toList();
    }

    /*public static List<Movie> searchGenre(String genre, List<Movie> movieList) {
        return movieList.stream().filter(movie -> movie.genres.contains(genre)).toList();
    }*/

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here

        movies.add(new Movie("test1", "des", "genre"));
        movies.add(new Movie("test2", "des", "genre"));
        movies.add(new Movie("test3", "des", "genre"));

        return movies;
    }
}
