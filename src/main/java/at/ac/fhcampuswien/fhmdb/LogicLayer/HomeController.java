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
    private static HomeController _instance;
    private HomeController() {
        super();
        allMovies = new ArrayList<>(Movie.initializeMovies());
    }
    public static HomeController getInstance() {
        if (_instance == null) _instance = new HomeController();
        return _instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_addToWatchlist, "Watchlist")); // use custom cell factory to display data
    }

    @Override
    protected void setMovies() {
        var test = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT, searchField.getText(), genreComboBox.getValue());
        if (test != null) allMovies = test;
    }
    private final ClickEventHandler<Movie> onClicked_addToWatchlist = (clickedMovie) -> {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            wrap.addToWatchlist(new WatchlistEntity(clickedMovie));
        } catch (DatabaseException dbe) {
            notifyUser(dbe, Alert.AlertType.ERROR);
        }
    };
    @Override
    public void update() {

    }
}