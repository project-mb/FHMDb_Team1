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
import java.util.ResourceBundle;

public class HomeController extends BaseController {
    public HomeController() { super(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        sortMoviesWithOther(allMovies);

        observableMovies.addAll(allMovies);

        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_addToWatchlist, "Watchlist")); // use custom cell factory to display data

        // filter button
        filterBtn.setOnAction(actionEvent -> {
            var test = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT, searchField.getText(), genreComboBox.getValue().toString());
            if (test != null) allMovies = test;

            filteredMovies = new ArrayList<>(getMoviesFiltered(allMovies, searchField.getText(), genreComboBox.getValue(), 0, 0));
            sortMoviesWithCurrent(filteredMovies);
            observableMovies.setAll(filteredMovies);
            movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_addToWatchlist, "Watchlist"));
        });
    }

    private final ClickEventHandler<Movie> onClicked_addToWatchlist = (clickedMovie) -> {
        try {
            WatchlistRepository wrap = new WatchlistRepository();
            wrap.addToWatchlist(new WatchlistEntity(clickedMovie));
        } catch (DatabaseException dbe) {
            notifyUser(dbe, Alert.AlertType.ERROR);
        }

    };
}