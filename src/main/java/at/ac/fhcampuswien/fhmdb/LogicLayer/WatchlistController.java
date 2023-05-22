package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;

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
        observableMovies.setAll(watchlistMovies);
    }
}
