package at.ac.fhcampuswien.fhmdb.LogicLayer;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends BaseController {
    public HomeController() { super(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        sortMoviesWithOther(allMovies);
        observableMovies.setAll(allMovies);
    }
}