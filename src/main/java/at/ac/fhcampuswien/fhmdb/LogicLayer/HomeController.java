package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.ObserverEvent;
import at.ac.fhcampuswien.fhmdb.PresentationLayer.MovieCell;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends BaseController {
    private static HomeController _instance;
    private HomeController() {
        super();
        WatchlistRepository.getInstance().add(this, ObserverEvent.ADD);
    }
    public static HomeController getInstance() {
        if (_instance == null) _instance = new HomeController();
        return _instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        movieListView.setCellFactory(movieListView -> new MovieCell(onClicked_addToWatchlist, "Watchlist"));
    }

    @Override
    protected void setMovies() {
        var test = MovieAPI.get(MovieAPI.MOVIES_ENDPOINT, searchField.getText(), genreComboBox.getValue(), releaseYearText.getText(), ratingLable.getText());
        if (test != null) allMovies = test;
        else allMovies = Movie.initializeMovies();
    }
    private final ClickEventHandler<Movie> onClicked_addToWatchlist = (clickedMovie) -> {
        try {
            WatchlistRepository wrap = WatchlistRepository.getInstance();
            wrap.addToWatchlist(new WatchlistEntity(clickedMovie));
        } catch (DatabaseException dbe) {
            notifyUser(Alert.AlertType.ERROR, dbe);
        }
    };

    @Override
    public void update(ObserverEvent event, WatchlistEntity movie) {
        if (movie != null) notifyUser("Update", "\"" + movie.title + "\" successfully added to watchlist!", Alert.AlertType.NONE);
        else notifyUser("Update", "Movie already on watchlist!", Alert.AlertType.INFORMATION);
    }
}