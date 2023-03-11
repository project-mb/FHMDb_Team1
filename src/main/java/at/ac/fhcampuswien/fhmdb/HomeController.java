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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static at.ac.fhcampuswien.fhmdb.model.Genre.__NONE__;

public class HomeController implements Initializable {
    @FXML
    public JFXButton filterBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();
    public List<Movie> filteredMovies = new ArrayList<>(allMovies);
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setValue(__NONE__);

        // filter button
        filterBtn.setOnAction(actionEvent -> {
            observableMovies.setAll(getMoviesFiltered(searchField.getText(), genreComboBox.getValue()));
            movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // sort button
        sortBtn.setOnAction(actionEvent -> {
            sort_movies();
            observableMovies.setAll(filteredMovies);
        });
    }

    public List<Movie> getMoviesFiltered(String searchQuery, Genre filter) {
        List<Movie> filteredMovies = Movie.getMoviesByGenre(allMovies, filter);
        List<Movie> queriedMovies = Movie.getMoviesBySearchQuery(filteredMovies, searchQuery);

        //TODO: change for queriedMovies when getMoviesBySearchQuery is implemented
        return filteredMovies;
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