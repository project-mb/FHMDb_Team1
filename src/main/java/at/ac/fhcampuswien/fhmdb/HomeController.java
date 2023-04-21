package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Genre;
import at.ac.fhcampuswien.fhmdb.model.Movie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.model.Genre.__NONE__;

public class HomeController implements Initializable {

    public static MovieAPI movieAPI = new MovieAPI();

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton filterBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public DatePicker releaseYearDatePicker;
    @FXML
    public Slider ratingSlider;

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
            var test = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT);
            if(test != null) allMovies = test;

            filteredMovies = new ArrayList<>(getMoviesFiltered(searchField.getText(), genreComboBox.getValue()));
            observableMovies.setAll(filteredMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell());
            //System.out.println(getMoviesBetweenYears(filteredMovies, 1995, 2001));
            //System.out.println(getMostPopularActor(filteredMovies));
            //System.out.println(getLongestMovieTitle(filteredMovies));
        });

        // sort button
        sortBtn.setOnAction(actionEvent -> {
            sort_movies();
            observableMovies.setAll(filteredMovies);
        });
    }

    public List<Movie> getMoviesFiltered(String searchQuery, Genre filter) {
        List<Movie> filteredMovies = getMoviesByGenre(allMovies, filter);
        return getMoviesByTitle(searchQuery, filteredMovies);
    }

    public static List<Movie> getMoviesByGenre(List<Movie> movies, Genre filter) {
        if (filter == __NONE__) return movies;
        return movies.stream().filter(movie -> movie.genres.contains(filter)).toList();
    }

    public static List<Movie> getMoviesByTitle(String title, List<Movie> movieList) {
        if (Objects.equals(title, "") || Objects.equals(title, " ")) {
            return movieList;
        }

        return movieList.stream().filter(movie -> movie.title.contains(title)).toList();
    }

    //TODO: Eduard
    public static String getMostPopularActor(List<Movie> movies) {

        return movies
                .stream()
                .map(movie -> movie.mainCast)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

                .toString();

        /*movies.stream()
                .filter(movie -> movie.mainCast.equals(movie.mainCast))
                .collect(Collectors.groupingBy(movie -> movie.mainCast, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);*/
        //return movies.stream()//.filter(movie -> movie.mainCast)
                //.stream()
                /*.filter(movies.stream().forEach(movie -> movie.title.length() > movie.title.length()))
                .filter(movie -> movie.mainCast.equals(movie.mainCast))
                .collect(Collectors.groupingBy(movie -> movie.mainCast, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .toString();*/
                //.ifPresent(System.out::println);
        //return null;
    }
    public static int getLongestMovieTitle(List<Movie> movies) {
        return 0;
    }
    public static long countMoviesFrom(List<Movie> movies, String director) {
        return 0;
    }
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> (movie.releaseYear <= startYear) && (movie.releaseYear >= endYear)).toList();
    }

    //TODO: Manuel
    public static List<Movie> getMoviesByReleaseYear(List<Movie> movies, int releaseYear) {
        return null;
    }
    public static List<Movie> getMoviesByRating(List<Movie> movies, int rating) {
        return null;
    }

    public void sort_movies() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            sortBtn.setText("Sort (desc)");
            sortAscending();
        } else {
            sortBtn.setText("Sort (asc)");
            sortDescending();
        }
    }
    public void sortAscending() { filteredMovies.sort(Comparator.comparing(Movie::getTitle)); }

    public void sortDescending() { filteredMovies.sort(Comparator.comparing(Movie::getTitle).reversed()); }
}