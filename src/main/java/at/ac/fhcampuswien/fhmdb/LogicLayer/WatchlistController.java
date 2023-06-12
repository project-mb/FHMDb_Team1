package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.ObserverEvent;
import at.ac.fhcampuswien.fhmdb.PresentationLayer.MovieCell;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class WatchlistController extends BaseController {
    private static WatchlistController _instance;
    private WatchlistController() {
        super();
        WatchlistRepository.getInstance().add(this, ObserverEvent.REMOVE);
    }
    public static WatchlistController getInstance() {
        if (_instance == null) _instance = new WatchlistController();
        return _instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_removeMovieFromWatchlist, "Remove")); // use custom cell factory to display data
    }

    @Override
    protected void setMovies() {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            allMovies.clear();
            allMovies.addAll(wrap.getAll().stream().map(Movie::new).toList());
        } catch (DatabaseException dbe) {
            BaseController.notifyUser(Alert.AlertType.ERROR, dbe);
        }
    }
    private final ClickEventHandler<Movie> onClicked_removeMovieFromWatchlist = (clickedMovie) -> {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            wrap.removeFromWatchlist(new WatchlistEntity(clickedMovie));
        } catch (DatabaseException dbe) {
            notifyUser(Alert.AlertType.ERROR, dbe);
        }
    };

    @Override
    public void update(ObserverEvent event, WatchlistEntity movie) {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            observableMovies.setAll(wrap.getAll().stream().map(Movie::new).toList());
            updateView();
            notifyUser("Update", "\"" + movie.title + "\" successfully removed from watchlist", Alert.AlertType.NONE);
        } catch (DatabaseException dbe) {
            notifyUser(Alert.AlertType.ERROR, dbe);
        }
    }
}
