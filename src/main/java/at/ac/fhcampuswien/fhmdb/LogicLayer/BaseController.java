package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.State.Sorter;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.IObserver;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre.__NONE__;

public abstract class BaseController implements Initializable, IObserver {
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

    protected ObservableList<Movie> observableMovies;
    protected List<Movie> allMovies;

    protected final Sorter sorter;

    public BaseController() {
        allMovies = new ArrayList<>();
        observableMovies = FXCollections.observableArrayList();
        sorter = new Sorter(observableMovies);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genreComboBox.getItems().setAll(Genre.values());
        genreComboBox.setValue(__NONE__);

        setMovies();
        observableMovies.setAll(allMovies);
        movieListView.setItems(observableMovies);

        sortBtn.setOnAction(actionEvent -> {
            sorter.state.onSort();
            sortBtn.setText(sorter.state.getName());
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
            String temp = String.format("%.4s", String.format("%2.1f", (double) newValue));
            ratingLable.textProperty().setValue(temp);
        });

        filterBtn.setOnAction(actionEvent -> updateView());
    }

    protected void updateView() {
        setMoviesFilteredWithCurrent();
        sorter.setMovieList(observableMovies);
        sorter.state.onCurrent();
    }

    protected abstract void setMovies();

    protected void setMoviesFilteredWithCurrent() {
        setMovies();
        int rlyf = (releaseYearText.getText().equals("")) ? 0 : Integer.parseInt(releaseYearText.getText());
        observableMovies.setAll(getMoviesFiltered(allMovies, searchField.getText(), genreComboBox.getValue(), rlyf, ratingSlider.getValue()));
    }
    public List<Movie> getMoviesFiltered(List<Movie> moviesToFilter, String searchQuery, Genre genreFilter, int releaseYearFilter, double ratingFilter) {
        List<Movie> filteredByRating = getMoviesByRating(moviesToFilter, ratingFilter);
        List<Movie> filteredByReleaseYear = getMoviesByReleaseYear(filteredByRating, releaseYearFilter);
        List<Movie> filteredByGenre = getMoviesByGenre(filteredByReleaseYear, genreFilter);

        return getMoviesByTitle(searchQuery, filteredByGenre);
    }

    protected static List<Movie> getMoviesByTitle(String title, List<Movie> movieList) {
        if (Objects.equals(title, "") || Objects.equals(title, " ")) {
            return movieList;
        }

        return movieList.stream().filter(movie -> movie.title.contains(title)).toList();
    }

    protected static List<Movie> getMoviesByGenre(List<Movie> movies, Genre genre) {
        if (genre == __NONE__) return movies;
        return movies.stream().filter(movie -> movie.genres.contains(genre)).toList();
    }

    protected static List<Movie> getMoviesByReleaseYear(List<Movie> movies, int releaseYear) {
        if (releaseYear == 0) return movies;
        return movies.stream().filter(movie -> movie.releaseYear == releaseYear).toList();
    }
    protected static List<Movie> getMoviesByRating(List<Movie> movies, double rating) {
        if (rating == 0) return movies;
        return movies.stream().filter(movie -> movie.rating == rating).toList();
    }

    protected static String getMostPopularActor(List<Movie> movies) {
        if (movies == null || movies.size() == 0) return "";

        return movies.stream()
                .map(movie -> movie.mainCast)
                .flatMap(Arrays::stream)
                .max(Comparator.comparing(s -> s))
                .orElse(null);
    }
    protected static int getLongestMovieTitle(List<Movie> movies) {
        if (movies == null || movies.size() == 0) return 0;

        return movies.stream()
                .max(Comparator.comparingInt(movie -> movie.title.length()))
                .get().title.length();
    }
    protected static long countMoviesFrom(List<Movie> movies, String director) {
        if (movies == null || movies.size() == 0) return 0;
        if (director.equals("")) return movies.size();

        return movies.stream()
                .map(movie -> movie.directors)
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(s -> s.equals(director))).size();
    }
    protected static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        if (movies == null || movies.size() == 0) return null;
        if (startYear <= 0 || endYear <= 0) return null;
        if (startYear > endYear) return null;

        return movies.stream()
                .filter(movie -> movie.releaseYear >= startYear && movie.releaseYear <= endYear)
                .toList();
    }

//    public static void notifyUser(String title, String content, Alert.AlertType type, Exception e) {
//        notifyUser(title, content + "\n\n" + e.getMessage(), type);
//    }
    public static void notifyUser(Alert.AlertType type, Exception e) {
        notifyUser("", e.getMessage(), type);
    }
    public static void notifyUser(String title, String content, Alert.AlertType type) {
        Alert userAlert = new Alert(type);
        if (type == Alert.AlertType.NONE) userAlert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        if (type == Alert.AlertType.ERROR) title = "!Error (notify admin)";
        userAlert.setTitle(title);
        userAlert.setContentText(content);
        userAlert.showAndWait();
    }
}
