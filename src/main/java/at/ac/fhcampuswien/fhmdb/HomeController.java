package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Genre;
import at.ac.fhcampuswien.fhmdb.model.Movie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.model.Genre.__NONE__;

public class HomeController implements Initializable {
    @FXML
    public JFXButton sortBtn;
    @FXML
    public TextField searchField;
    @FXML
    public JFXComboBox<Genre> genreComboBox;
    @FXML
    public TextField releaseYearText;
    @FXML
    public Slider ratingSlider;
    @FXML
    public Label ratingLable;
    @FXML
    public JFXButton filterBtn;

    @FXML
    public JFXListView<Movie> movieListView;
    public List<Movie> allMovies = Movie.initializeMovies();
    public List<Movie> filteredMovies = new ArrayList<>(allMovies);
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.setAll(allMovies);

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setValue(__NONE__);

        // filter button
        filterBtn.setOnAction(actionEvent -> {
            var test = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT, searchField.getText(), genreComboBox.getValue().toString());
            if (test != null) allMovies = test;

            filteredMovies = new ArrayList<>(getMoviesFiltered(searchField.getText(), genreComboBox.getValue(), 0, 0));
            observableMovies.setAll(filteredMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // sort button
        sortBtn.setOnAction(actionEvent -> {
            sort_movies(filteredMovies);
            observableMovies.setAll(filteredMovies);
        });

        releaseYearText.textProperty().addListener((observable, oldValue, newValue) -> {
            String temp = "";
            if (newValue.equals("")) return;
            try {
                Integer.parseInt(newValue);
                if (newValue.length() > 4) temp = oldValue;
                else temp = newValue;
            } catch (Exception e) {
                temp = oldValue;
            } finally {
                releaseYearText.textProperty().set(temp);
            }
        });

        ratingSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            ratingLable.textProperty().setValue(String.format("%.4s", String.format("%2.1f", (double) newValue)));
        });
    }

    public List<Movie> getMoviesFiltered(String searchQuery, Genre genreFilter, int releaseYearFilter, int ratingFilter) {
        List<Movie> filteredByRating = getMoviesByRating(allMovies, ratingFilter);
        List<Movie> filteredByReleaseYear = getMoviesByReleaseYear(filteredByRating, releaseYearFilter);
        List<Movie> filteredByGenre = getMoviesByGenre(filteredByReleaseYear, genreFilter);

        return getMoviesByTitle(searchQuery, filteredByGenre);
    }

    public static List<Movie> getMoviesByTitle(String title, List<Movie> movieList) {
        if (Objects.equals(title, "") || Objects.equals(title, " ")) {
            return movieList;
        }

        return movieList.stream().filter(movie -> movie.title.contains(title)).toList();
    }

    public static List<Movie> getMoviesByGenre(List<Movie> movies, Genre filter) {
        if (filter == __NONE__) return movies;
        return movies.stream().filter(movie -> movie.genres.contains(filter)).toList();
    }

    public static List<Movie> getMoviesByReleaseYear(List<Movie> movies, int releaseYear) {
        if (releaseYear == 0) return movies;
        return movies.stream().filter(movie -> movie.releaseYear == releaseYear).toList();
    }
    public static List<Movie> getMoviesByRating(List<Movie> movies, int rating) {
        if (rating == 0) return movies;
        return movies.stream().filter(movie -> movie.rating == rating).toList();
    }

    //TODO: fix getMostPopularActor
    public static String getMostPopularActor(List<Movie> movies) {
        if (movies == null || movies.size() == 0) return "";

        return movies.stream()
                .map(movie -> movie.mainCast)
                .flatMap(Arrays::stream)
                .max(Comparator.comparing(s -> s))
                .get();
    }
    public static int getLongestMovieTitle(List<Movie> movies) {
        if (movies == null || movies.size() == 0) return 0;
        return movies.stream()
                .max(Comparator.comparingInt(movie -> movie.title.length()))
                .get().title.length();
    }
    public static long countMoviesFrom(List<Movie> movies, String director) {
        if (movies == null || movies.size() == 0) return 0;
        if (director.equals("")) return movies.size();

        return movies.stream()
                .map(movie -> movie.directors)
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(s -> s.equals(director))).size();
    }
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        if (movies == null || movies.size() == 0) return null;
        if (startYear <= 0 || endYear <= 0) return null;
        if (startYear > endYear) return null;

        return movies.stream()
                .filter(movie -> movie.releaseYear >= startYear && movie.releaseYear <= endYear)
                .toList();
    }

    public void sort_movies(List<Movie> movies) {
        if (sortBtn.getText().equals("Sort (asc)")) {
            sortBtn.setText("Sort (desc)");
            sortAscending(movies);
        } else {
            sortBtn.setText("Sort (asc)");
            sortDescending(movies);
        }
    }
    protected void sortAscending(List<Movie> movies) { movies.sort(Comparator.comparing(Movie::getTitle)); }

    protected void sortDescending(List<Movie> movies) { movies.sort(Comparator.comparing(Movie::getTitle).reversed()); }

}