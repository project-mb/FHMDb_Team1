package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import at.ac.fhcampuswien.fhmdb.PresentationLayer.MovieCell;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WatchlistController extends BaseController {
    public List<Movie> watchlistMovies = new ArrayList<>();
    public WatchlistController() { super(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            watchlistMovies.addAll(wrap.getAll().stream().map(Movie::new).toList());
            observableMovies.addAll(watchlistMovies);
        } catch (DatabaseException dbe) {
            BaseController.notifyUser(dbe, Alert.AlertType.ERROR);
        }

        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_removeMovieFromWatchlist, "Remove")); // use custom cell factory to display data

        // filter button
        filterBtn.setOnAction(actionEvent -> {
            var test = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT, searchField.getText(), genreComboBox.getValue().toString());
            if (test != null) allMovies = test;

            filteredMovies = new ArrayList<>(getMoviesFiltered(watchlistMovies, searchField.getText(), genreComboBox.getValue(), 0, 0));
            sortMoviesWithCurrent(filteredMovies);
            observableMovies.setAll(filteredMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_removeMovieFromWatchlist, "Remove"));
        });
    }

    private final ClickEventHandler<Movie> onClicked_removeMovieFromWatchlist = (clickedMovie) -> {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            wrap.removeFromWatchlist(new WatchlistEntity(clickedMovie));
            observableMovies.remove(clickedMovie);
        } catch (DatabaseException dbe) {
            notifyUser(dbe, Alert.AlertType.ERROR);
        }
    };
}
