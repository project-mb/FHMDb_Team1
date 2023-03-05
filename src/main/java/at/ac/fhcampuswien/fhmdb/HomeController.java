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
import java.util.ArrayList;
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
    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setValue(__NONE__);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        filterBtn.setOnAction(actionEvent -> {
            observableMovies.setAll(getMoviesFiltered(searchField.getText(), genreComboBox.getValue()));
            movieListView.setCellFactory(movieListView -> new MovieCell());
            movieListView.refresh();
        });

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });
    }

    public List<Movie> getMoviesFiltered(String searchQuery, Genre filter) {
        List<Movie> filteredMovies = getMoviesByGenre(allMovies, filter);
        List<Movie> queriedMovies = getMoviesBySearchQuery(filteredMovies, searchQuery);

        //TODO: change for queriedMovies when getMoviesBySearchQuery is implemented
        return filteredMovies;
    }

    public List<Movie> getMoviesByGenre(List<Movie> movies, Genre filter) {
        if (filter == __NONE__) return movies;

        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie m : allMovies) { if (m.getGenres().contains(filter)) filteredMovies.add(m); }

        return filteredMovies;
    }

    //TODO (@Eduard): implement method
    private List<Movie> getMoviesBySearchQuery(List<Movie> movies, String searchQuery) {
        return null;
    }
}