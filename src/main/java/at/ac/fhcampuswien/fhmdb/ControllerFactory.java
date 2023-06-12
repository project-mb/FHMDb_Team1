package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.LogicLayer.HomeController;
import at.ac.fhcampuswien.fhmdb.LogicLayer.MainController;
import at.ac.fhcampuswien.fhmdb.LogicLayer.WatchlistController;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {
    @Override
    public Object call(Class<?> _class) {
        switch (_class.getSimpleName()) {
            case "MainController" -> { return MainController.getInstance(); }
            case "HomeController" -> { return HomeController.getInstance(); }
            case "WatchlistController" -> { return WatchlistController.getInstance(); }
            default -> System.out.println("Error");
        }

        return null;
    }
}
