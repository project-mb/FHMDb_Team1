package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import at.ac.fhcampuswien.fhmdb.PresentationLayer.MovieCell;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WatchlistController extends BaseController {
    private static WatchlistController _instance;
    private WatchlistController() {
        super();
        allMovies = new ArrayList<>();
    }
    public static WatchlistController getInstance() {
        if (_instance == null) _instance = new WatchlistController();
        return _instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            allMovies.addAll(wrap.getAll().stream().map(Movie::new).toList());
            observableMovies.addAll(allMovies);
        } catch (DatabaseException dbe) {
            BaseController.notifyUser(dbe, Alert.AlertType.ERROR);
        }

        movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_removeMovieFromWatchlist, "Remove")); // use custom cell factory to display data
    }

    @Override
    protected void setMovies() { }
    private final ClickEventHandler<Movie> onClicked_removeMovieFromWatchlist = (clickedMovie) -> {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            wrap.removeFromWatchlist(new WatchlistEntity(clickedMovie));
            observableMovies.remove(clickedMovie);
        } catch (DatabaseException dbe) {
            notifyUser(dbe, Alert.AlertType.ERROR);
        }
    };
    @Override
    public void update() {

    }
}
